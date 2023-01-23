package com.skypro.java.petshelterbot.bot.botapi.handler;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.bot.botapi.InputMessageHandler;
import com.skypro.java.petshelterbot.repository.VolunteerRepository;
import com.skypro.java.petshelterbot.service.MessageService;
import com.skypro.java.petshelterbot.service.UserStateService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.skypro.java.petshelterbot.message.BotCommands.CALL_VOLUNTEER;
import static com.skypro.java.petshelterbot.message.BotCommands.TO_MAIN_MENU;

/**
 * Handler for CALL_VOLUNTEER_STATE state
 */

@Component
public class CallVolunteerHandler implements InputMessageHandler {

    private final MessageService messageService;
    private final UserStateService userStateService;
    private final VolunteerRepository volunteerRepository;

    public CallVolunteerHandler(MessageService messageService, UserStateService userStateService, VolunteerRepository volunteerRepository) {
        this.messageService = messageService;
        this.userStateService = userStateService;
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersMessage(message);
    }

    @Override
    public BotState getHandler() {
        return BotState.CALL_VOLUNTEER_STATE;
    }
    long userChatId;
    long newUserChatId;
    /**
     * Processes the text from CALL_VOLUNTEER_STATE menu
     *
     * @param message from update
     * @return {@link SendMessage}
     */
    private SendMessage processUsersMessage(Message message) {
        long volunteerChatId = volunteerRepository.getVolunteerById(4L).getChatId();
        userChatId = message.getChatId();
        SendMessage messageToUser = null;
        BotState botState = userStateService.getUserState(message).getState();

        if (botState.equals(BotState.CALL_VOLUNTEER_STATE)) {
            newUserChatId = message.getChatId();
            messageToUser = messageService.sendReplyMessage(userChatId, "\uD83D\uDEE0 Я волонтёр, чем могу помочь",
                    messageService.generateMenuKeyBoard("Закончить диалог"));
            userStateService.setBotState(userChatId, BotState.CHAT_TO_VOLUNTEER_STATE);
        }
        if (botState.equals(BotState.CHAT_TO_VOLUNTEER_STATE)) {
            messageToUser = messageService.sendMessage(volunteerChatId, message.getText());
        }
        if (botState.equals(BotState.VOLUNTEER_STATE)) {
            messageToUser = messageService.sendMessage(newUserChatId, message.getText());
        }
        if (botState.equals(BotState.START_STATE)) {
            messageToUser = messageService.sendReplyMessage(
                    userChatId,
                    "Чат с волонтером закрыт",
                    messageService.generateMenuKeyBoard(CALL_VOLUNTEER, TO_MAIN_MENU));
        }

        return messageToUser;
    }
}
