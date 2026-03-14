package com.jeniatyt.service.file.impl

import com.jeniatyt.controller.button.impl.ViewButtonContract
import com.jeniatyt.controller.button.impl.ViewButtonLogistics
import com.jeniatyt.controller.button.impl.ViewButtonStorePdf
import com.jeniatyt.controller.button.impl.ViewButtonStoreXLSX
import com.jeniatyt.controller.button.impl.ViewButtonTravelOZON
import com.jeniatyt.controller.button.impl.ViewButtonTravelWB
import com.jeniatyt.service.file.FileService
import com.jeniatyt.service.file.WriteResponse
import com.jeniatyt.service.file.WriteStatus
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Service
class FileServiceImpl(
    @Value("\${file.store-path:./data}")
    private val uploadDir: String,
) : FileService {

    private val log = LoggerFactory.getLogger(FileServiceImpl::class.java)
    private val buttonInfo = mapOf(
        1 to ViewButtonContract(),
        2 to ViewButtonTravelOZON(),
        3 to ViewButtonTravelWB(),
        4 to ViewButtonLogistics(),
        5 to ViewButtonStorePdf(),
        6 to ViewButtonStoreXLSX(),
    )

    override fun write(file: MultipartFile, buttonId: Int): WriteResponse {
        val name = buttonInfo[buttonId]?.getName()
        val extension = getExtension(file)
        val fullName = name + extension

        try {
            val uploadDir = Paths.get(uploadDir)
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir)
                log.info("Создана директория для загрузок: ${this.uploadDir}")
            }

            getFile(name!!)?.delete()

            val filePath = uploadDir.resolve(fullName)

            file.inputStream.use { input ->
                Files.copy(input, filePath)
            }

        } catch (e: Exception) {
            log.error("Файл не записался", e)
            return WriteResponse(WriteStatus.FAIL, fullName)
        }

        return WriteResponse(WriteStatus.SUCCESS, fullName)
    }

    override fun getFile(fileName: String): File? {
        val currentDir = File(uploadDir)

        val file = currentDir.walk()
            .filter { it.isFile }
            .find { file ->
                val nameWithoutExtension = file.nameWithoutExtension
                nameWithoutExtension == fileName
            }

            return file
    }

    private fun getExtension(file: MultipartFile): String {
        return file.originalFilename!!.substring(file.originalFilename!!.indexOf("."))
    }
}
