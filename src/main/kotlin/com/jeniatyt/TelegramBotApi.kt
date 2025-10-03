package com.jeniatyt

import com.jeniatyt.properties.TelegramProperties
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.generics.BotOptions
import org.telegram.telegrambots.meta.generics.LongPollingBot
import org.telegram.telegrambots.util.WebhookUtils

/**
 * Компонент, предоставляющий доступ к Telegram Bot API
 */
@Component
class TelegramBotApi(
    private val telegramProperties: TelegramProperties,
    private var messageHandler: MessageHandler,
    private val executeService: ExecuteService
) : LongPollingBot {

    override fun getBotUsername() = telegramProperties.name

    override fun onUpdateReceived(update: Update) {
        if (update.hasCallbackQuery()) {
            executeService.execute(AnswerCallbackQuery(update.callbackQuery.id))
        }

        messageHandler.handle(update)
    }

    override fun getBotToken(): String {
        return telegramProperties.token
    }

    override fun getOptions(): BotOptions {
        return executeService.options
    }

    override fun clearWebhook() {
        WebhookUtils.clearWebhook(executeService)
    }
}
