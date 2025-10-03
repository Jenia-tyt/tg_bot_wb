package com.jeniatyt.handler.impl

import com.jeniatyt.command.impl.MainCommand
import com.jeniatyt.entity.Activity
import com.jeniatyt.handler.Handler
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class CallManagerHandler : Handler {

    override fun accept(command: String, userId: Long?, activity: Activity?): Boolean {
        return MainCommand.CALL_MANAGER.getCommand() == command
    }

    override fun handle(update: Update, userId: Long?, activity: Activity?) {
        //nothing
    }
}
