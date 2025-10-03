package com.jeniatyt

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.EnableScheduling
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession


/**
 * Основной класс приложения.
 *
 */
@SpringBootApplication
@EnableScheduling
@ConfigurationPropertiesScan
class InnotechRandomCoffeeApplication(
    private val botApp: TelegramBotApi

) {
    private val log = KotlinLogging.logger {}

    @EventListener(ApplicationReadyEvent::class)
    fun registerBot(event: ApplicationReadyEvent) {

        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotsApi.registerBot(botApp)

        log.info {
            """
            ===================
            THE BOT HAS STARTED
            ===================
            """
        }
    }
}

fun main(args: Array<String>) {
    runApplication<InnotechRandomCoffeeApplication>(*args)
}

