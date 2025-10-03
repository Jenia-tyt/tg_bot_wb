package com.jeniatyt.button.supplier

import com.jeniatyt.button.ButtonSupplier
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class CommandButtonSupplier(
    private val description: String,
    private val command: String
) : ButtonSupplier {

    override fun get(): InlineKeyboardButton {
        return InlineKeyboardButton().apply {
            text = description
            callbackData = command
        }
    }
}
