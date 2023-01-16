package com.skypro.java.petshelterbot.bot.botapi;

import com.skypro.java.petshelterbot.bot.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Message handler
 *
 * @author evnag
 */
public interface InputMessageHandler {

    /**
     * Handles the user`s message
     *
     * @param message from update
     * @return {@link SendMessage}
     */
    SendMessage handle(Message message);

    /**
     * Gets the handler name
     *
     * @return BotState
     */
    BotState getHandler();
}
