package com.jeniatyt.controller.button.impl

import com.jeniatyt.controller.button.ViewButton
import com.jeniatyt.file.FilesName
import org.springframework.web.multipart.MultipartFile

class ViewButtonStoreXLSX: ViewButton {

    override fun getId(): Int {
        return 6
    }

    override fun getName(): String {
        return FilesName.PRISE_STORE_XLSX_FILE_NAME
    }
}
