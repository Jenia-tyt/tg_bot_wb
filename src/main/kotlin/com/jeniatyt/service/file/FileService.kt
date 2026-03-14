package com.jeniatyt.service.file

import org.springframework.web.multipart.MultipartFile
import java.io.File

interface FileService {

    fun write(file: MultipartFile, buttonId: Int): WriteResponse

    fun getFile(fileName: String): File?
}
