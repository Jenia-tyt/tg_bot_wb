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
class QuestionEndHandler(
    private val messageService: MessageService,
    private val questionnaireRepository: QuestionnaireRepository,
    private val actionRepository: ActionRepository
) : Handler {

    private val log = KotlinLogging.logger {}

    override fun order(): Int {
        return 9
    }

    override fun accept(command: String, userId: Long?, activity: Activity?): Boolean {
        return QuestionnaireCommand.Q9.getCommand() == command ||
            activity?.command != null && activity.command == QuestionnaireCommand.Q9.getCommand()
    }

    override fun handle(update: Update, userId: Long?, activity: Activity?) {
        if (userId == null) {
            log.error { "UserId can't be null" }
            return
        }

        val messageText = update.getMessageText()

        val userName = update.message.from.userName
        var data = questionnaireRepository.findById(userId).orElse(null)
        if (data == null) {
            val entity = com.jeniatyt.entity.Questionnaire(
                userId,
                QuestionnaireData().apply { q9 = userName; q10 = messageText }
            )
            data = questionnaireRepository.save(entity)
        } else {
            data.data?.q9 = userName
            data.data?.q10 = messageText
            data = questionnaireRepository.save(data)
        }

        if (activity != null) {
            activity.command = ""
            actionRepository.save(activity)
        } else {
            val newActivity = Activity(userId, "")
            actionRepository.save(newActivity)
        }

        val qData = data.data
        messageService.sendMessageWithKeyboard(
            update.getChatIdAsString(),
            Button.stepEnd(QuestionnaireCommand.Q8.getCommand()),
            Questionnaire.questionEnd,
            arrayOf(
                qData?.q1 ?: "",
                qData?.q2 ?: "",
                qData?.q3 ?: "",
                qData?.q4 ?: "",
                qData?.q5 ?: "",
                qData?.q6 ?: "",
                qData?.q7 ?: "",
                qData?.q8 ?: "",
                qData?.q9 ?: "",
                qData?.q10 ?: ""
            )
        )
    }
}
