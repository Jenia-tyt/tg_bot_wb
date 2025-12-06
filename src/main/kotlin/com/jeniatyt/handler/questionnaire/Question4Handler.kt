package com.jeniatyt.handler.questionnaire

import com.jeniatyt.button.impl.Button
import com.jeniatyt.command.impl.QuestionnaireCommand
import com.jeniatyt.dto.QuestionnaireData
import com.jeniatyt.entity.Activity
import com.jeniatyt.extension.getChatIdAsString
import com.jeniatyt.extension.getMessageText
import com.jeniatyt.handler.Handler
import com.jeniatyt.message.Questionnaire
import com.jeniatyt.repository.ActionRepository
import com.jeniatyt.repository.QuestionnaireRepository
import com.jeniatyt.service.MessageService
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class Question4Handler(
    private val messageService: MessageService,
    private val questionnaireRepository: QuestionnaireRepository,
    private val actionRepository: ActionRepository
) : Handler {

    private val log = KotlinLogging.logger {}

    override fun order(): Int {
        return 4
    }

    override fun accept(command: String, userId: Long?, activity: Activity?): Boolean {
        return QuestionnaireCommand.Q3.getCommand() == command ||
            activity?.command != null && activity.command == QuestionnaireCommand.Q3.getCommand()
    }

    override fun handle(update: Update, userId: Long?, activity: Activity?) {
        if (userId == null) {
            log.error { "UserId can't be null" }
            return
        }

        val messageText = getDefaultQuestion(update.getMessageText())
        questionnaireRepository.findById(userId).ifPresentOrElse(
            { questionnaire -> questionnaire.data?.q3 = messageText },
            {
                val entity = com.jeniatyt.entity.Questionnaire(userId, QuestionnaireData().apply { q3 = messageText })
                questionnaireRepository.save(entity)
            }
        )

        if (activity != null) {
            activity.command = QuestionnaireCommand.Q4.getCommand()
            actionRepository.save(activity)
        } else {
            val newActivity = Activity(userId, QuestionnaireCommand.Q4.getCommand())
            actionRepository.save(newActivity)
        }

        messageService.sendMessageWithKeyboard(
            update.getChatIdAsString(),
            Button.baseStep(QuestionnaireCommand.Q2.getCommand(), QuestionnaireCommand.Q4.getCommand()),
            Questionnaire.question4
        )
    }
}
