package com.jeniatyt.controller.button.impl

import com.jeniatyt.controller.button.ViewButton
import com.jeniatyt.file.FilesName
import org.springframework.web.multipart.MultipartFile

class ViewButtonStorePdf: ViewButton {

    override fun getId(): Int {
        return 5
    }

    override fun getName(): String {
        return FilesName.PRISE_STORE_PDF_FILE_NAME
    }
}
