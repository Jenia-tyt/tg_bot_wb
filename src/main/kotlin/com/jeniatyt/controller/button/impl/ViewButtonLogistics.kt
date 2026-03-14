package com.jeniatyt.controller.button.impl

import com.jeniatyt.controller.button.ViewButton
import com.jeniatyt.file.FilesName
import org.springframework.web.multipart.MultipartFile

class ViewButtonLogistics: ViewButton {

    override fun getId(): Int {
        return 4
    }

    override fun getName(): String {
        return FilesName.PRISE_LOGISTICS_PDF_FILE_NAME
    }
}
