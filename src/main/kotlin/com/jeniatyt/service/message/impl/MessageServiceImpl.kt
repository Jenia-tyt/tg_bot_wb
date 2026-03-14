package com.jeniatyt.service.message.impl

import com.jeniatyt.ExecuteService
import com.jeniatyt.button.impl.Button
import com.jeniatyt.message.Message
import com.jeniatyt.service.file.FileService
import com.jeniatyt.service.message.MessageService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import java.text.MessageFormat

@Service
class MessageServiceImpl(
    private val executeService: ExecuteService,
    private val fileService: FileService
) : MessageService {

    override fun sendErrorMessage(chatId: String) {
        sendMessageWithKeyboard(chatId, Button.ALL_BUTTON, Message.ERROR)
    }

    override fun sendMessage(chatId: String, template: String, args: Array<String>, transformation: (SendMessage) -> SendMessage) {
        val message = SendMessage()
        message.text = MessageFormat.format(template, *sanitize(args))
        message.parseMode = ParseMode.MARKDOWNV2
        message.chatId = chatId
        message.allowSendingWithoutReply = false

        executeService.execute(transformation.invoke(message))
    }

    override fun sendMessageWithKeyboard(chatId: String, replyMarkup: InlineKeyboardMarkup, template: String, args: Array<String>) {
        val message = SendMessage()
        message.text = MessageFormat.format(template, *sanitize(args))
        message.parseMode = ParseMode.MARKDOWNV2
        message.chatId = chatId
        message.replyMarkup = replyMarkup

        executeService.execute(message)
    }

    override fun sendMessageDocument(chatId: String, fileName: String) {
        val file = fileService.getFile(fileName)
        if (file == null) {
            sendMessage(chatId, Message.FILE_ERROR, arrayOf(fileName))
            return
        }

        val inputFile = InputFile(file.inputStream(), file.name)

        val message = SendDocument()
        message.document = inputFile
        message.parseMode = ParseMode.MARKDOWNV2
        message.chatId = chatId

        executeService.execute(message)
    }

    /**
     * Экранирует с '\' специальные символы разметки MarkdownV2 Telegram Bot API.
     * https://core.telegram.org/bots/api#markdownv2-style
     *
     * @param args аргументы без экранирования специальных символов
     * @return аргументы с экранированием специальных символов
     */
    private fun sanitize(args: Array<String>): Array<String> = args.map { arg -> arg.replace(MATCH_TG_ESCAPE_SYMBOLS) { "\\${it.value}" } }.toTypedArray()

    companion object {
        /** Регулярное выражение, находящее символы: '_', '*', '[', ']', '(', ')', '~', '`', '>', '#', '+', '-', '=', '|', '{', '}', '.', '!' в строке */
        val MATCH_TG_ESCAPE_SYMBOLS = Regex("[\\_\\*\\[\\]\\(\\)\\~\\`\\>\\#\\+\\-\\=\\|\\{\\}\\.\\!]")
    }
}
