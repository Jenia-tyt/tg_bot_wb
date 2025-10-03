package com.jeniatyt

import com.jeniatyt.handler.Handler
import com.jeniatyt.service.MessageService
import inno.tech.extension.getChatId
import inno.tech.extension.getMessageTextOrNull
import inno.tech.extension.getUserIdOrNull
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MessageHandler(
    private val handlers: List<Handler>,
    private val messageService: MessageService
) {

    fun handle(update: Update) {
        val command: String? = update.getMessageTextOrNull()
        val userId: Long? = update.getUserIdOrNull()

        if (command == null || userId == null) {
            return
        }

        handlers.firstOrNull { it.accept(command, userId) }?.handle(update, userId)
            ?: messageService.sendErrorMessage(update.getChatId())
    }
}
