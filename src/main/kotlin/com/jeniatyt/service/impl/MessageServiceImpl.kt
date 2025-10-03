package com.jeniatyt.service.impl

import com.jeniatyt.ExecuteService
import com.jeniatyt.service.MessageService
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(private val executeService: ExecuteService) : MessageService {

    override fun sendErrorMessage(chatId: Long) {
        TODO("Not yet implemented")
    }

}
