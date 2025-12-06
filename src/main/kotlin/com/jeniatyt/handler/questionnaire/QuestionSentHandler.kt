package com.jeniatyt.handler.questionnaire

import com.jeniatyt.button.impl.Button
import com.jeniatyt.command.impl.QuestionnaireCommand
import com.jeniatyt.entity.Activity
import com.jeniatyt.extension.getChatIdAsString
import com.jeniatyt.handler.Handler
import com.jeniatyt.message.Message
import com.jeniatyt.message.Questionnaire
import com.jeniatyt.properties.ChatProperties
import com.jeniatyt.repository.QuestionnaireRepository
import com.jeniatyt.service.MessageService
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class QuestionSentHandler(
    private val messageService: MessageService,
    private val chatProperties: ChatProperties,
    private val questionnaireRepository: QuestionnaireRepository
) : Handler {

    private val log = KotlinLogging.logger {}

    override fun order(): Int {
        return Int.MAX_VALUE
    }

    override fun accept(command: String, userId: Long?, activity: Activity?): Boolean {
        return QuestionnaireCommand.SENT.getCommand() == command
    }

    override fun handle(update: Update, userId: Long?, activity: Activity?) {
        if (userId == null) {
            log.error { "UserId can't be null" }
            return
        }

        questionnaireRepository.findById(userId).ifPresentOrElse(
            { questionnaire ->
                val qData = questionnaire.data

                messageService.sendMessage(
                    chatProperties.supportChatId,
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

                messageService.sendMessageWithKeyboard(
                    update.getChatIdAsString(),
                    Button.ALL_BUTTON,
                    Questionnaire.question10
                )
            },
            {
                log.error { "Сообщение не отправлено, так как не найдена анкета" }
                messageService.sendMessage(update.getChatIdAsString(), Message.ERROR)
            }
        )
    }
}
