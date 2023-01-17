package com.skypro.java.petshelterbot.bot.botapi.handler;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.bot.botapi.InputMessageHandler;
import com.skypro.java.petshelterbot.service.MessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Handler for CALL_VOLUNTEER_STATE state
 */

@Component
public class CallVolunteerHandler implements InputMessageHandler {

    private final MessageService messageService;

    public CallVolunteerHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersMessage(message);
    }

    @Override
    public BotState getHandler() {
        return BotState.CALL_VOLUNTEER_STATE;
    }

    /**
     * Processes the text from CALL_VOLUNTEER_STATE menu
     *
     * @param message from update
     * @return {@link SendMessage}
     */
    private SendMessage processUsersMessage(Message message) {
        long chatId = message.getChatId();
        return messageService.sendMessage(chatId, "\uD83D\uDEE0 Button is under maintenance");
    }
}
