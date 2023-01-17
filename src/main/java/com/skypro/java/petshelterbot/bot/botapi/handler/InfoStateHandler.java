package com.skypro.java.petshelterbot.bot.botapi.handler;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.bot.botapi.InputMessageHandler;
import com.skypro.java.petshelterbot.service.MessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.skypro.java.petshelterbot.message.BotCommands.*;
import static com.skypro.java.petshelterbot.message.BotOutMessages.*;

/**
 * Handler for INFO menu state
 *
 * @author evnag
 */

@Component
public class InfoStateHandler implements InputMessageHandler {

    private final MessageService messageService;

    public InfoStateHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersMessage(message);
    }

    @Override
    public BotState getHandler() {
        return BotState.INFO_STATE;
    }

    /**
     * Processes the text messages from INFO menu
     *
     * @param message from update
     * @return {@link SendMessage}
     */
    private SendMessage processUsersMessage(Message message) {
        long chatId = message.getChatId();
        String userAnswer = message.getText();
        SendMessage messageToUser;

        switch (userAnswer) {
            case INFO ->
                    messageToUser = messageService.sendReplyMessage(chatId, SELECT_OPTION, messageService.generateMenuKeyBoard(
                            ABOUT_SHELTER,
                            SHELTER_ADDRESS,
                            SAFETY_MEASURES,
                            CALL_VOLUNTEER,
                            TO_MAIN_MENU
                    ));
            case ABOUT_SHELTER -> messageToUser = messageService.sendMessage(chatId, NEW_USER_INFO_START);
            case SHELTER_ADDRESS -> messageToUser = messageService.sendMessage(chatId, NEW_USER_INFO_SHELTER);
            case SAFETY_MEASURES -> messageToUser = messageService.sendMessage(chatId, NEW_USER_INFO_REGULATIONS);
            default -> messageToUser = messageService.sendMessage(chatId, UNKNOWN_COMMAND);
        }
        return messageToUser;
    }
}
