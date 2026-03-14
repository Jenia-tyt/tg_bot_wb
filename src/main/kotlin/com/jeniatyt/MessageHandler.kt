package com.jeniatyt

import com.jeniatyt.extension.getChatId
import com.jeniatyt.extension.getChatIdAsString
import com.jeniatyt.extension.getMessageTextOrNull
import com.jeniatyt.extension.getUserIdOrNull
import com.jeniatyt.handler.Handler
import com.jeniatyt.repository.ActionRepository
import com.jeniatyt.service.message.MessageService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MessageHandler(
    unSortHandlers: List<Handler>,
    private val messageService: MessageService,
    private val actionRepository: ActionRepository
) {
    private final val sortHandlers: List<Handler> = unSortHandlers.sorted()

    @Transactional
    fun handle(update: Update) {
        val command: String? = update.getMessageTextOrNull()
        val userId: Long? = update.getUserIdOrNull()

        if (command == null || userId == null) {
            return
        }

        val action = actionRepository.findById(userId).orElse(null)

        try {
            sortHandlers.firstOrNull { it.accept(command, userId, action) }?.handle(update, userId, action)
                ?: messageService.sendErrorMessage(update.getChatIdAsString())
        } catch (e: Exception) {
            messageService.sendErrorMessage(update.getChatIdAsString())
        }
    }
}
