package com.jeniatyt.service.impl

import com.jeniatyt.ExecuteService
import com.jeniatyt.button.impl.Button
import com.jeniatyt.message.Message
import com.jeniatyt.service.MessageService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import java.io.InputStream
import java.text.MessageFormat

@Service
class MessageServiceImpl(private val executeService: ExecuteService) : MessageService {

    override fun sendErrorMessage(chatId: Long) {
        sendMessageWithKeyboard(chatId.toString(), Button.ALL_BUTTON, Message.ERROR)
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
        val inputStream: InputStream? = MessageServiceImpl::class.java.getResourceAsStream("/price/${fileName}")
        val inputFile = InputFile(inputStream, fileName)

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
