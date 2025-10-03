package com.jeniatyt.handler

import org.telegram.telegrambots.meta.api.objects.Update

interface Handler {

    fun accept(command: String, userId: Long?): Boolean


    fun handle(update: Update, userId: Long?)
}
