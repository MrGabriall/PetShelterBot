package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.bot.botapi.BotStateContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.skypro.java.petshelterbot.message.BotCommands.*;

/**
 * Define methods to processing updates & incoming messages
 *
 * @author evnag
 */

@Service
public class UpdateService {

    private final UserStateService userStateService;
    private final BotStateContext botStateContext;

    public UpdateService(UserStateService userStateService,
                         BotStateContext botStateContext) {
        this.userStateService = userStateService;
        this.botStateContext = botStateContext;
    }

    /**
     * Processing updates
     *
     * @param update from bot
     * @return SendMessage
     */
    public SendMessage handleUpdate(Update update) {
        SendMessage messageToSend = null;
        if (update.hasMessage() && update.getMessage().hasText()) {
            messageToSend = handleInputMessage(update.getMessage());
        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
            messageToSend = handleInputPhoto(update.getMessage());
        }
        return messageToSend;
    }

    /**
     * Processing incoming text messages & switching states
     *
     * @param message from update
     * @return SendMessage
     */
    private SendMessage handleInputMessage(Message message) {
        String commandText = message.getText();
        long chatId = message.getChatId();
        BotState botState;
        SendMessage messageToSend;

        switch (commandText) {
            case START, TO_MAIN_MENU -> botState = BotState.START_STATE;
            case INFO, ABOUT_SHELTER, SHELTER_ADDRESS, SAFETY_MEASURES -> botState = BotState.INFO_STATE;
            case HOW_TO_ADOPT, SEND_CONTACTS -> botState = BotState.HOW_TO_ADOPT_STATE;// SEND_CONTACTS временно обрабатывается тут
            case SEND_REPORT -> botState = BotState.FILLING_REPORT;

//            case SEND_CONTACTS -> botState = BotState.SEND_CONTACTS_STATE;
            case CALL_VOLUNTEER -> botState = BotState.CALL_VOLUNTEER_STATE;
            default -> botState = userStateService.getUserState(message).getState();
        }
        userStateService.setBotState(chatId, botState);
        messageToSend = botStateContext.processInput(botState, message);

        return messageToSend;
    }

    /**
     * Processing incoming photo messages
     *
     * @param message from update
     * @return SendMessage
     */
    private SendMessage handleInputPhoto(Message message) {
        BotState botState;
        SendMessage messageToSend;
        botState = userStateService.getUserState(message).getState();
        messageToSend = botStateContext.processInput(botState, message);
        return messageToSend;
    }
}
