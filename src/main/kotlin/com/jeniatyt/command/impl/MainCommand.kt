package com.jeniatyt.command.impl

import com.jeniatyt.command.Command

/**
 * Команда бота.
 *
 * @param command код команды
 */
enum class MainCommand(private val command: String): Command {

    /** Начало */
    START("/start"),

    /** Получение информации о боте */
    INDIVIDUAL_CALCULATION("/individual_calculation"),

    /** Получение прайса складских услуг */
    PRAISE_STORE("/price_store"),

    /** Получение прайса логистик */
    PRAISE_LOGISTICS("/praise_logistics"),

    /** Позвать менеджера */
    CALL_MANAGER("/call_manager");

    override fun getCommand(): String {
        return command
    }
}
