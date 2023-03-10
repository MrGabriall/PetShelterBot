package com.skypro.java.petshelterbot.bot.botapi.handler;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.bot.botapi.InputMessageHandler;
import com.skypro.java.petshelterbot.repository.VolunteerRepository;
import com.skypro.java.petshelterbot.service.MessageService;
import com.skypro.java.petshelterbot.service.UserStateService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.skypro.java.petshelterbot.message.BotCommands.CALL_VOLUNTEER;
import static com.skypro.java.petshelterbot.message.BotCommands.TO_MAIN_MENU;

/**
 * Handler for SEND_CONTACTS_STATE state
 */

@Component
public class SendContactStateHandler implements InputMessageHandler {

    private final MessageService messageService;
    private final UserStateService userStateService;
    private  final VolunteerRepository volunteerRepository;

    public final static Pattern PATTERN = Pattern.compile("^(\\+7|7|8)?[\\s\\-]?\\(?[489][0-9]{2}\\)?[\\s\\-]?[0-9]{3}[\\s\\-]?[0-9]{2}[\\s\\-]?[0-9]{2}$");

    public SendContactStateHandler(MessageService messageService, UserStateService userStateService, VolunteerRepository volunteerRepository) {
        this.messageService = messageService;
        this.userStateService = userStateService;
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public SendMessage handle(Message message) {
        if (userStateService.getUserState(message).getState().equals(BotState.SEND_CONTACTS_STATE)) {
            userStateService.setBotState(message.getChatId(), BotState.FILLING_CONTACTS_STATE);
        }
        return processUsersMessage(message);
    }

    @Override
    public BotState getHandler() {
        return BotState.SEND_CONTACTS_STATE;
    }

    /**
     * Processes the text from SEND_CONTACTS_STATE menu
     *
     * @param message from update
     * @return {@link SendMessage}
     */
    private SendMessage processUsersMessage(Message message) {
        long chatId = message.getChatId();
        String userAnswer = message.getText();
        String userName = message.getChat().getUserName();
        SendMessage messageToUser;
        BotState botState = userStateService.getUserState(message).getState();
        long volunteerChatId = volunteerRepository.getVolunteerById(1L).getChatId();
        ReplyKeyboardMarkup keyboardMarkup = messageService.generateMenuKeyBoard(CALL_VOLUNTEER, TO_MAIN_MENU);
        keyboardMarkup.setInputFieldPlaceholder("?????? ?????? ?????????? ??????????: +79855310868");

        if (botState.equals(BotState.FILLING_CONTACTS_STATE)) {
            messageToUser = messageService.sendReplyMessage(
                    chatId, "?????????????? ?????????? ????????????????",
                    keyboardMarkup
            );
            userStateService.setBotState(chatId, BotState.CONTACTS_SENT_STATE);
            return messageToUser;
        }
        if (botState.equals(BotState.CONTACTS_SENT_STATE)) {
            messageToUser = checkContacts(userAnswer, volunteerChatId, chatId, userName);
            return messageToUser;
        }
        return messageService.sendMessage(
                chatId, "?????????????? ?????????? ???????????????? ??????????????????!!)");
    }

    /**
     * Checks the message for compliance with the pattern
     * Sets states
     * Sends message to volunteer if matcher matches
     * @param message from update
     * @param volunteerChatId volunteer`s chat ID
     * @param chatId userChatId
     * @return {@link SendMessage}
     */
    private SendMessage checkContacts(String message, long volunteerChatId, long chatId, String userName) {
        SendMessage messageToUser;
        String messageToSend = String.format(
                """
                        ?????? ????????????????????????: %s
                        ??????????????: %s
                        ID ????????: %s""", userName, message, chatId);

        Matcher matcher = PATTERN.matcher(message);
        if (matcher.matches()) {
            messageToUser = messageService.sendMessage(volunteerChatId, messageToSend);
            userStateService.setBotState(chatId, BotState.START_STATE);
        } else {
            messageToUser = messageService.sendMessage(
                    chatId, "?????????????? ?????????? ???????????????? ?? ???????????????????? ??????????????!!)");
            userStateService.setBotState(chatId, BotState.CONTACTS_SENT_STATE);
        }
        return messageToUser;
    }
}
