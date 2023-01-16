package com.skypro.java.petshelterbot.bot.botapi.handler;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.bot.botapi.InputMessageHandler;
import com.skypro.java.petshelterbot.service.MessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Default state handler
 *
 * @author evnag
 */
@Component
public class DefaultMessageHandler implements InputMessageHandler {

    private final MessageService messageService;

    public DefaultMessageHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Processes a message in the context of a specific state
     *
     * @param message from update
     * @return SendMessage
     */
    @Override
    public SendMessage handle(Message message) {
        return processInputMessage(message);
    }

    /**
     * Gets the handler name
     *
     * @return BotState
     */
    @Override
    public BotState getHandler() {
        return BotState.START_STATE;
    }

    /**
     * Processes the user's text message and sends Welcome message
     *
     * @param message from update
     * @return {@link SendMessage}
     */
    private SendMessage processInputMessage(Message message) {
        long chatId = message.getChatId();
        String userName = message.getChat().getUserName();

        //        userStateService.setBotState(chatId,FILLING_REPORT);

        return messageService.startCommandReceived(chatId, userName);
    }
}
