package com.jeniatyt.controller.impl

import com.jeniatyt.controller.FileController
import com.jeniatyt.service.file.FileService
import com.jeniatyt.service.file.WriteStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = ["*"])
class FileControllerImpl(
    private val fileService: FileService
): FileController {

    @PostMapping("/{buttonId}")
    override fun uploadContract(file: MultipartFile, buttonId: Int): ResponseEntity<Map<String, Any>> {
        val map = write(file, buttonId)
        return ResponseEntity.ok(map)
    }

    private fun write(file: MultipartFile, buttonId: Int): Map<String, Any> {
        val response = fileService.write(file, buttonId)
        return if (WriteStatus.FAIL == response.status) {
            mapOf(
                "success" to false,
                "message" to "Не удалось загрузить файл смотри логи",
                "fileId" to response.id
            )
        } else {
            mapOf(
                "success" to true,
                "message" to "Файл успешно загружен",
                "fileId" to response.id
            )
        }
    }
}
