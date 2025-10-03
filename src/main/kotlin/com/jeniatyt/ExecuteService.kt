package com.jeniatyt

import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.bots.DefaultBotOptions

/**
 * ExecuteService
 *
 * @author Evgeniy_Uvarov
 */
@Service
class ExecuteService(telegramProperties: TelegramProperties) : DefaultAbsSender(DefaultBotOptions(), telegramProperties.token)
