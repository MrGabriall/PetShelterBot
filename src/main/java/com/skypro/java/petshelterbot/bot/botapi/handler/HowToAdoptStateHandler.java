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
 * Handler for HOW_TO_ADOPT state
 *
 * @author evnag
 */

@Component
public class HowToAdoptStateHandler implements InputMessageHandler {
    private final MessageService messageService;

    public HowToAdoptStateHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processInputMessage(message);
    }

    @Override
    public BotState getHandler() {
        return BotState.HOW_TO_ADOPT_STATE;
    }

    /**
     * Processes the text from HOW_TO_ADOPT menu
     *
     * @param message from update
     * @return {@link SendMessage}
     */
    private SendMessage processInputMessage(Message message) {
        long chatId = message.getChatId();
        String userAnswer = message.getText();
        SendMessage messageToUser;

        switch (userAnswer) {
            case HOW_TO_ADOPT ->
                    messageToUser = messageService.sendReplyMessage(chatId, POTENTIAL_ANIMAL_OWNER_INFO_HELLO, messageService
                            .generateMenuKeyBoard(
                                    RULES_BEFORE_ADOPTING,
                                    REQUIRED_DOCUMENTS,
                                    PET_TRANSPORTATION,
                                    PUPPY_HOUSE,
                                    ADULT_DOG_HOUSE,
                                    DOG_HANDLERS_TIPS,
                                    DOG_HANDLERS_LIST,
                                    WHY_REJECTED,
                                    SEND_CONTACTS,
                                    CALL_VOLUNTEER,
                                    TO_MAIN_MENU
                            ));
            case RULES_BEFORE_ADOPTING ->
                    messageToUser = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_REGULATIONS_ANIMAL);
            case REQUIRED_DOCUMENTS ->
                    messageToUser = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_LIST_THE_DOCUMENTS);
            case PET_TRANSPORTATION ->
                    messageToUser = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_RECOMMENDATIONS_TRANSPORT);
            case PUPPY_HOUSE ->
                    messageToUser = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_LIST_RECOMMENDATIONS_HOME);
            case ADULT_DOG_HOUSE ->
                    messageToUser = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_LIST_RECOMMENDATIONS_HOME_BIG_DOG);
            case DOG_HANDLERS_TIPS ->
                    messageToUser = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_ADVICE_COMMUNICATION_ANIMAL);
            case DOG_HANDLERS_LIST ->
                    messageToUser = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_RECOMMENDATIONS_ON_CYNOLOGIST);
            case WHY_REJECTED ->
                    messageToUser = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_RENOUNCEMENT);
            default -> messageToUser = messageService.sendMessage(chatId, UNKNOWN_COMMAND);
        }

        return messageToUser;
    }
}
