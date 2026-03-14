package com.jeniatyt.controller.button.impl

import com.jeniatyt.controller.button.ViewButton
import com.jeniatyt.file.FilesName
import org.springframework.web.multipart.MultipartFile

class ViewButtonTravelWB: ViewButton {

    override fun getId(): Int {
        return 3
    }

    override fun getName(): String {
        return FilesName.SCHEDULE_TRAVEL_WB
    }
}
