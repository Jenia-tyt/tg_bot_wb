package com.jeniatyt.controller.button.impl

import com.jeniatyt.controller.button.ViewButton
import com.jeniatyt.file.FilesName

class ViewButtonContract: ViewButton {

    override fun getId(): Int {
        return 1
    }

    override fun getName(): String {
        return FilesName.CONTRACT
    }
}
