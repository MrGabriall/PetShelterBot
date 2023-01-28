package com.skypro.java.petshelterbot.bot.botapi.handler;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.bot.botapi.InputMessageHandler;
import com.skypro.java.petshelterbot.service.MessageService;
import com.skypro.java.petshelterbot.service.UserStateService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.skypro.java.petshelterbot.message.BotCommands.*;

/**
 * Handler for CHOOSE_SHELTER_STATE state
 */

@Component
public class ChooseShelterHandler implements InputMessageHandler {

    private final MessageService messageService;
    private final UserStateService userStateService;

    public ChooseShelterHandler(MessageService messageService, UserStateService userStateService) {
        this.messageService = messageService;
        this.userStateService = userStateService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processInputMessage(message);
    }

    @Override
    public BotState getHandler() {
        return BotState.CHOOSE_SHELTER_STATE;
    }

    /**
     * Processes the text from CHOOSE_SHELTER_STATE menu
     *
     * @param message from update
     * @return {@link SendMessage}
     */
    private SendMessage processInputMessage(Message message) {
        long chatId = message.getChatId();
        String userAnswer = message.getText();
        String userName = message.getChat().getUserName();
        SendMessage messageToUser = switch (userAnswer) {
            case "\uD83D\uDE3A CAT SHELTER" -> howToAdoptCatMessage(message);
            case "\uD83D\uDC36 DOG SHELTER" -> howToAdoptDogMessage(message);
            default -> messageService.startCommandFromDefault(chatId, userName);
        };

        userStateService.setBotState(chatId, BotState.CHOOSE_SHELTER_STATE);
        return messageToUser;
    }

    /**
     * Send Welcome message+reply markup keyboard for Dog shelter user
     *
     * @param message from update
     * @return SendMessage
     */
    private SendMessage howToAdoptDogMessage(Message message) {
        long chatId = message.getChatId();
        return messageService.sendReplyMessage(
                chatId,
                "\uD83D\uDC36 Вы находитесь в меню информации приюта для собак \uD83D\uDC36",
                messageService.generateMenuKeyBoard(
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
    }

    /**
     * Send Welcome message+reply markup keyboard for Dog shelter user
     *
     * @param message from update
     * @return SendMessage
     */
    private SendMessage howToAdoptCatMessage(Message message) {
        long chatId = message.getChatId();
        return messageService.sendReplyMessage(
                chatId,
                "\uD83D\uDE3A Вы находитесь в меню информации приюта для котов \uD83D\uDE3A",
                messageService.generateMenuKeyBoard(
                        RULES_BEFORE_ADOPTING,
                        REQUIRED_DOCUMENTS,
                        PET_TRANSPORTATION,
                        CAT_HOUSE,
                        WHY_REJECTED,
                        SEND_CONTACTS,
                        CALL_VOLUNTEER,
                        TO_MAIN_MENU
                ));
    }
}
