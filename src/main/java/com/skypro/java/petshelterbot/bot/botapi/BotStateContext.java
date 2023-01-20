package com.skypro.java.petshelterbot.bot.botapi;

import com.skypro.java.petshelterbot.bot.BotState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Message handlers for each state
 *
 * @author evnag
 */

@Component
public class BotStateContext {
    /**
     * Stores a specific handler by key-state
     */
    private final Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandler(), handler));
    }

    /**
     * Processes the user's message in the state`s context
     *
     * @param state   current state
     * @param message from message
     * @return {@link SendMessage}
     */
    public SendMessage processInput(BotState state, Message message) {
        InputMessageHandler currentHandler = findHandler(state);
        return currentHandler.handle(message);
    }

    /**
     * Finds the handler by state
     *
     * @param state current state
     * @return {@link InputMessageHandler}
     */
    private InputMessageHandler findHandler(BotState state) {
        if (isReportFillingState(state)) {
            return messageHandlers.get(BotState.FILLING_REPORT);
        } else if (isInfoState(state)) {
            return messageHandlers.get(BotState.INFO_STATE);
        } else if (isHowToAdoptState(state)) {
            return messageHandlers.get(BotState.HOW_TO_ADOPT_STATE);
        }

        return messageHandlers.get(state);
    }

    /**
     * Checks the state
     *
     * @param state current state
     * @return boolean
     */
    private boolean isReportFillingState(BotState state) {
        return switch (state) {
            case SEND_DIET_STATE,
                    SEND_HEALTH_STATE,
                    SEND_BEHAVIOR_STATE,
                    SEND_PHOTO_STATE,
                    FILLING_REPORT,
                    REPORT_FILLED_STATE -> true;
            default -> false;
        };
    }

    /**
     * Checks the state
     *
     * @param state current state
     * @return boolean
     */
    private boolean isInfoState(BotState state) {
        return state == BotState.INFO_STATE;
    }

    /**
     * Checks the state
     *
     * @param state current state
     * @return boolean
     */
    private boolean isHowToAdoptState(BotState state) {
        return state == BotState.HOW_TO_ADOPT_STATE;
    }
}
