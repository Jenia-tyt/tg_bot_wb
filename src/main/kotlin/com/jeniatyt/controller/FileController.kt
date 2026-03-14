package com.jeniatyt.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

interface FileController {

    fun uploadContract(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("buttonId") buttonId: Int,
    ): ResponseEntity<Map<String, Any>>

}
