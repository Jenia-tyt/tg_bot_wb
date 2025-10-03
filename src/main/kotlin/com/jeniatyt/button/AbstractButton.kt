package com.jeniatyt.button

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

abstract class AbstractButton {

    protected fun creatButtonsString(commands: Collection<ButtonSupplier>, lineSize: Int): InlineKeyboardMarkup {
        val buttonsInLine = mutableListOf<List<InlineKeyboardButton>>()

        var line = mutableListOf<InlineKeyboardButton>()
        val lastIndex = commands.size - 1
        commands.forEachIndexed { index, entry ->
            if (line.size >= lineSize) {
                buttonsInLine.add(line)
                line = mutableListOf()
            }

            line.add(entry.get())
            if (index == lastIndex) {
                buttonsInLine.add(line)
            }
        }

        return InlineKeyboardMarkup().apply { keyboard = buttonsInLine }
    }

    protected fun creatButtonsCommand(commands: Collection<ButtonSupplier>) = creatButtonsString(commands, 1)
}
