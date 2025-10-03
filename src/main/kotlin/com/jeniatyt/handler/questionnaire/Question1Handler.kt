package com.jeniatyt.handler.questionnaire

import com.jeniatyt.command.impl.MainCommand
import com.jeniatyt.command.impl.QuestionnaireCommand
import com.jeniatyt.entity.Activity
import com.jeniatyt.extension.getChatIdAsString
import com.jeniatyt.handler.Handler
import com.jeniatyt.message.Questionnaire
import com.jeniatyt.repository.ActionRepository
import com.jeniatyt.service.MessageService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class Question1Handler(
    private val messageService: MessageService,
    private val actionRepository: ActionRepository
) : Handler {

    override fun order(): Int {
        return 0
    }

    override fun accept(command: String, userId: Long?, activity: Activity?): Boolean {
        return MainCommand.INDIVIDUAL_CALCULATION.getCommand() == command
    }

    override fun handle(update: Update, userId: Long?, activity: Activity?) {
        if (activity == null) {
            val newActivity = Activity(userId, QuestionnaireCommand.Q1.getCommand())
            actionRepository.save(newActivity)
        } else {
            activity.command = QuestionnaireCommand.Q1.getCommand()
            actionRepository.save(activity)
        }

        messageService.sendMessage(update.getChatIdAsString(), Questionnaire.question1)
    }
}
