package com.jeniatyt.button.impl

import com.jeniatyt.button.AbstractButton
import com.jeniatyt.button.supplier.CommandButtonSupplier
import com.jeniatyt.button.supplier.RefButtonSupplier
import com.jeniatyt.command.impl.MainCommand
import com.jeniatyt.command.impl.QuestionnaireCommand
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup


object Button : AbstractButton() {
    private const val INDIVIDUAL_CALCULATION_DESCRIPTION = "Индивидуальный расчет"
    private const val PRICE_STORE_DESCRIPTION = "Прайс «Складские услуги»"
    private const val PRISE_LOGISTICS_DESCRIPTION = "Прайс «Логистика»"
    private const val CALL_MANAGER_DESCRIPTION = "Позвать менеджера"
    private const val BACK = "Назад"
    private const val SENT = "Отправить"
    private const val NEXT = "Оставить поле пустым"

    private const val SUPPORT_CHAT = "mosfulfillment"

    val ALL_BUTTON: InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(INDIVIDUAL_CALCULATION_DESCRIPTION, MainCommand.INDIVIDUAL_CALCULATION.getCommand()),
            CommandButtonSupplier(PRICE_STORE_DESCRIPTION, MainCommand.PRAISE_STORE.getCommand()),
            CommandButtonSupplier(PRISE_LOGISTICS_DESCRIPTION, MainCommand.PRAISE_LOGISTICS.getCommand()),
            RefButtonSupplier(CALL_MANAGER_DESCRIPTION, SUPPORT_CHAT)
        )
    )

    val WITHOUT_LOGISTICS_BUTTON: InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(INDIVIDUAL_CALCULATION_DESCRIPTION, MainCommand.INDIVIDUAL_CALCULATION.getCommand()),
            CommandButtonSupplier(PRICE_STORE_DESCRIPTION, MainCommand.PRAISE_STORE.getCommand()),
            RefButtonSupplier(CALL_MANAGER_DESCRIPTION, SUPPORT_CHAT)
        )
    )

    val WITHOUT_STORE_BUTTON: InlineKeyboardMarkup = creatButtonsCommand(
        listOf(
            CommandButtonSupplier(INDIVIDUAL_CALCULATION_DESCRIPTION, MainCommand.INDIVIDUAL_CALCULATION.getCommand()),
            CommandButtonSupplier(PRISE_LOGISTICS_DESCRIPTION, MainCommand.PRAISE_LOGISTICS.getCommand()),
            RefButtonSupplier(CALL_MANAGER_DESCRIPTION, SUPPORT_CHAT)
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
