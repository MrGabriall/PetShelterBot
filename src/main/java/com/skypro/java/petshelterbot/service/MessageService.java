package com.skypro.java.petshelterbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static com.skypro.java.petshelterbot.command.BotCommands.*;
import static com.skypro.java.petshelterbot.message.BotOutMessages.NEW_USER_HELLO;

@Service
public class MessageService {

    public SendMessage startCommandReceived(long chatId, String name) {
        String messageToSend = "Привет, " + name + "! " + NEW_USER_HELLO;
        return sendReplyMessage(chatId, messageToSend, generateMenuKeyBoard(INFO, HOW_TO_ADOPT, CALL_VOLUNTEER, SEND_REPORT));
    }

    public SendMessage sendMessage(long chatId, String messageToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageToSend);
        return message;
    }

    public SendMessage sendReplyMessage(long chatId, String messageToSend, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(messageToSend);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

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
