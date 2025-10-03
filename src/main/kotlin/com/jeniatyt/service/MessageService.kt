package com.jeniatyt.service

/**
 * Сервис отправки сообщений
 */
interface MessageService {

    /**
     * Отправляет главное меню бота, если обработчик сообщения не найден.
     * @param chatId идентификатор чата адресата
     */
    fun sendErrorMessage(chatId: Long)

}
