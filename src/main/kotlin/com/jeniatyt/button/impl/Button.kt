package com.jeniatyt.button.impl

import com.jeniatyt.button.AbstractButton
import com.jeniatyt.button.supplier.CommandButtonSupplier
import com.jeniatyt.button.supplier.RefButtonSupplier
import com.jeniatyt.command.impl.MainCommand
import com.jeniatyt.command.impl.QuestionnaireCommand
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup


object Button : AbstractButton() {
    private const val INDIVIDUAL_CALCULATION = "Индивидуальный расчет"
    private const val PRICE_STORE = "Прайс «Складские услуги»"
    private const val PRISE_LOGISTICS = "Прайс «Логистика»"
    private const val CALL_MANAGER = "Позвать менеджера"
    private const val TELEGRAM_CHANEL = "Наш телеграм-канал"
    private const val SCHEDULE_TRAVEL = "График поездок"
    private const val CONTRACT = "Скачать договор"
    private const val BACK = "Назад"
    private const val SENT = "Отправить"
    private const val NEXT = "Оставить поле пустым"

    private const val SUPPORT_CHAT = "mosfulfillment"
    private const val CHANEL = "mosfulfill"

    val ALL_BUTTON: InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(INDIVIDUAL_CALCULATION, MainCommand.INDIVIDUAL_CALCULATION.getCommand()),
            CommandButtonSupplier(PRICE_STORE, MainCommand.PRAISE_STORE.getCommand()),
            CommandButtonSupplier(PRISE_LOGISTICS, MainCommand.PRAISE_LOGISTICS.getCommand()),
            CommandButtonSupplier(SCHEDULE_TRAVEL, MainCommand.SCHEDULE_TRAVEL.getCommand()),
            CommandButtonSupplier(CONTRACT, MainCommand.CONTRACT.getCommand()),
            RefButtonSupplier(CALL_MANAGER, SUPPORT_CHAT),
            RefButtonSupplier(TELEGRAM_CHANEL, CHANEL),
        )
    )

    val WITHOUT_LOGISTICS_BUTTON: InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(INDIVIDUAL_CALCULATION, MainCommand.INDIVIDUAL_CALCULATION.getCommand()),
            CommandButtonSupplier(PRICE_STORE, MainCommand.PRAISE_STORE.getCommand()),
            CommandButtonSupplier(SCHEDULE_TRAVEL, MainCommand.SCHEDULE_TRAVEL.getCommand()),
            CommandButtonSupplier(CONTRACT, MainCommand.CONTRACT.getCommand()),
            RefButtonSupplier(CALL_MANAGER, SUPPORT_CHAT)
        )
    )

    val WITHOUT_STORE_BUTTON: InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(INDIVIDUAL_CALCULATION, MainCommand.INDIVIDUAL_CALCULATION.getCommand()),
            CommandButtonSupplier(PRISE_LOGISTICS, MainCommand.PRAISE_LOGISTICS.getCommand()),
            CommandButtonSupplier(SCHEDULE_TRAVEL, MainCommand.SCHEDULE_TRAVEL.getCommand()),
            CommandButtonSupplier(CONTRACT, MainCommand.CONTRACT.getCommand()),
            RefButtonSupplier(CALL_MANAGER, SUPPORT_CHAT)
        )
    )

    val WITHOUT_SCHEDULE_TRAVEL_BUTTON: InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(INDIVIDUAL_CALCULATION, MainCommand.INDIVIDUAL_CALCULATION.getCommand()),
            CommandButtonSupplier(PRICE_STORE, MainCommand.PRAISE_STORE.getCommand()),
            CommandButtonSupplier(PRISE_LOGISTICS, MainCommand.PRAISE_LOGISTICS.getCommand()),
            CommandButtonSupplier(CONTRACT, MainCommand.CONTRACT.getCommand()),
            RefButtonSupplier(CALL_MANAGER, SUPPORT_CHAT)
        )
    )

    val WITHOUT_CONTRACT_BUTTON: InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(INDIVIDUAL_CALCULATION, MainCommand.INDIVIDUAL_CALCULATION.getCommand()),
            CommandButtonSupplier(PRICE_STORE, MainCommand.PRAISE_STORE.getCommand()),
            CommandButtonSupplier(PRISE_LOGISTICS, MainCommand.PRAISE_LOGISTICS.getCommand()),
            CommandButtonSupplier(SCHEDULE_TRAVEL, MainCommand.SCHEDULE_TRAVEL.getCommand()),
            RefButtonSupplier(CALL_MANAGER, SUPPORT_CHAT)
        )
    )

    fun stepBack(step: String): InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(BACK, step)
        )
    )

    fun stepNext(step: String): InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(NEXT, step)
        )
    )

    fun baseStep(back: String, next: String): InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(BACK, back),
            CommandButtonSupplier(NEXT, next)
        )
    )

    fun stepEnd(step: String): InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(BACK, step),
            CommandButtonSupplier(SENT, QuestionnaireCommand.SENT.getCommand())
        )
    )
}
