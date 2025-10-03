package com.jeniatyt.handler

import com.jeniatyt.entity.Activity
import org.telegram.telegrambots.meta.api.objects.Update

interface Handler: Comparable<Handler> {

    fun order(): Int {
        return Int.MIN_VALUE
    }

    fun accept(command: String, userId: Long?, activity: Activity?): Boolean

    fun handle(update: Update, userId: Long?, activity: Activity?)

    override fun compareTo(other: Handler): Int {
        return order().compareTo(other.order())
    }
}
