package com.jeniatyt.service

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup

/**
 * Сервис отправки сообщений
 */
interface MessageService {

    /**
     * Отправляет главное меню бота, если обработчик сообщения не найден.
     * @param chatId идентификатор чата адресата
     */
    fun sendErrorMessage(chatId: Long)

    /**
     * Отправляет шаблонизированное сообщение.
     * @param chatId идентификатор чата адресата
     * @param template шаблон сообщения
     * @param args список аргументов для подстановки в шаблон
     * @param transformation дополнительные трансформации сообщения
     */
    fun sendMessage(
        chatId: String,
        template: String,
        args: Array<String> = emptyArray(),
        transformation: (SendMessage) -> SendMessage = { it }
    )

    /**
     * Отправляет шаблонизированное сообщение с Inline клавиатурой
     * @param chatId идентификатор чата адресата
     * @param replyMarkup inline клавиатура
     * @param template шаблон сообщения
     * @param args список аргументов для подстановки в шаблон
     */
    fun sendMessageWithKeyboard(
        chatId: String,
        replyMarkup: InlineKeyboardMarkup,
        template: String,
        args: Array<String> = emptyArray()
    )

    /**
     * Отправляет файл из ресурсов
     * @param chatId идентификатор чата адресата
     * @param fileName имя файла
     */
    fun sendMessageDocument(chatId: String, fileName: String)

}
