package com.jeniatyt.properties

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Конфигурации подключения к Telegram Bot API.
 * @param name имя бота
 * @param token токен
 */
@ConfigurationProperties("telegram")
data class TelegramProperties(
    val name: String,
    val token: String
)
