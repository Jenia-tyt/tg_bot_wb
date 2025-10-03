package com.jeniatyt.button

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

interface ButtonSupplier {

    fun get() : InlineKeyboardButton
}
