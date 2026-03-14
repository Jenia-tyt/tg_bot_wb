package com.jeniatyt.controller.button.impl

import com.jeniatyt.controller.button.ViewButton
import com.jeniatyt.file.FilesName
import org.springframework.web.multipart.MultipartFile

class ViewButtonTravelOZON: ViewButton {

    override fun getId(): Int {
        return 2
    }

    override fun getName(): String {
        return FilesName.SCHEDULE_TRAVEL_OZON
    }
}
