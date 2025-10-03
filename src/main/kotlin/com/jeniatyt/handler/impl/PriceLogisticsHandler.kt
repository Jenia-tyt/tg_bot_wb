package com.jeniatyt.handler.impl

import com.jeniatyt.button.impl.Button
import com.jeniatyt.command.impl.MainCommand
import com.jeniatyt.entity.Activity
import com.jeniatyt.extension.getChatIdAsString
import com.jeniatyt.file.FilesName
import com.jeniatyt.handler.Handler
import com.jeniatyt.message.Message
import com.jeniatyt.service.MessageService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class PriceLogisticsHandler(private val messageService: MessageService) : Handler {

    override fun accept(command: String, userId: Long?, activity: Activity?): Boolean {
        return MainCommand.PRAISE_LOGISTICS.getCommand() == command
    }

    override fun handle(update: Update, userId: Long?, activity: Activity?) {
        val chatId = update.getChatIdAsString()
        messageService.sendMessageDocument(chatId, FilesName.PRISE_LOGISTICS_PDF_FILE_NAME)
        messageService.sendMessageWithKeyboard(chatId, Button.WITHOUT_LOGISTICS_BUTTON, Message.PRICE_STORE)
    }
}
