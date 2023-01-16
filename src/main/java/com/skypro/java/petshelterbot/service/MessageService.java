package com.skypro.java.petshelterbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static com.skypro.java.petshelterbot.message.BotCommands.*;
import static com.skypro.java.petshelterbot.message.BotOutMessages.NEW_USER_HELLO;

/**
 * Describes methods to processing messages
 *
 * @author evnag
 */

@Service
public class MessageService {

    /**
     * Send Welcome message
     *
     * @param chatId value from update
     * @param name   userName value from update
     * @return SendMessage w/ Start keyboard
     */
    public SendMessage startCommandReceived(long chatId, String name) {
        String messageToSend = "Привет, " + name + "! " + NEW_USER_HELLO;
        return sendReplyMessage(chatId, messageToSend, generateMenuKeyBoard(INFO, HOW_TO_ADOPT, CALL_VOLUNTEER, SEND_REPORT));
    }

    /**
     * Send any message
     *
     * @param chatId        chatId value from update
     * @param messageToSend message from BotMessages
     * @return SendMessage
     */
    public SendMessage sendMessage(long chatId, String messageToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageToSend);
        return message;
    }

    /**
     * Send any message w/ reply markup keyboard
     *
     * @param chatId         chatId value from update
     * @param messageToSend  message from BotMessages
     * @param keyboardMarkup keyboardMarkup selected keyboard
     * @return SendMessage
     */
    public SendMessage sendReplyMessage(long chatId, String messageToSend, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageToSend);
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

    /**
     * Generates a reply keyboard from String varargs
     *
     * @param buttons String values with text for menu buttons, @NotNull
     * @return ReplyKeyboardMarkup - menu keyboard
     */
    public ReplyKeyboardMarkup generateMenuKeyBoard(String... buttons) {

        ReplyKeyboardMarkup menuKeyBoard = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (String button : buttons) {
            row.add(button);
            if (row.size() == 1) {
                keyboardRows.add(row);
            } else if (row.size() > 1) {
                row = new KeyboardRow();
            }
        }

        menuKeyBoard.setKeyboard(keyboardRows);
        menuKeyBoard.setResizeKeyboard(true);
        menuKeyBoard.setSelective(true);
        return menuKeyBoard;
    }

}
