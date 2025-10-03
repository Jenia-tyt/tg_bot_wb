package com.jeniatyt.button.supplier

import com.jeniatyt.button.ButtonSupplier
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

class RefButtonSupplier(
    private val description: String,
    private val ref: String
) : ButtonSupplier {

    override fun get(): InlineKeyboardButton {
        return InlineKeyboardButton().apply {
            text = description
            url = "t.me/${ref}"
        }
    }
}
