package com.skypro.java.petshelterbot;

import com.skypro.java.petshelterbot.service.MessageService;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static com.skypro.java.petshelterbot.message.BotCommands.*;
import static com.skypro.java.petshelterbot.message.BotCommands.CALL_VOLUNTEER;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author evnag
 */
public class MessageServiceTest {
    private final MessageService messageService = new MessageService();

    @Test
    public void startCommandFromDefaultTest() {
        String messageToSend = "Привет, Vasyan! Выбери приют!!";
        SendMessage actualMessage = messageService.startCommandFromDefault(12345, "Vasyan");
        assertThat(actualMessage).isNotNull();
        assertThat(actualMessage)
                .isEqualTo(messageService.sendReplyMessage(12345, messageToSend, messageService.generateMenuKeyBoard("\uD83D\uDE3A CAT SHELTER", "\uD83D\uDC36 DOG SHELTER", INFO)));
    }

    @Test
    public void startCommandFromOwnerTest() {
        String messageToSend = "Привет, Vasyan!  Заполните  форму ежедневного отчета";
        SendMessage actualMessage = messageService.startCommandFromOwner(12345, "Vasyan");
        assertThat(actualMessage).isNotNull();
        assertThat(actualMessage)
                .isEqualTo(messageService.sendReplyMessage(12345, messageToSend, messageService.generateMenuKeyBoard(INFO, HOW_TO_ADOPT, SEND_REPORT, CALL_VOLUNTEER)));
    }

    @Test
    void sendMessageTest() {
        String messageToSend = "Привет, test!";
        SendMessage message = new SendMessage(String.valueOf(123), messageToSend);

        SendMessage actualMessage = messageService.sendMessage(123, "Привет, test!");
        assertThat(actualMessage).isNotNull();
        assertThat(actualMessage.getChatId()).isEqualTo(message.getChatId());
        assertThat(actualMessage.getText()).isEqualTo(message.getText());

        assertThat(actualMessage).isEqualTo(new SendMessage(String.valueOf(123), messageToSend));
    }

    @Test
    void sendMessageNegative() {
        String messageToSend = "Привет, test!";
        SendMessage actualMessage = messageService.sendMessage(123, "Привет, NegativeTest!");
        assertThat(actualMessage).isNotEqualTo(new SendMessage(String.valueOf(123), messageToSend));
    }

    @Test
    void sendReplyMessageTest() {
        String messageToSend = "Привет, test!";
        SendMessage message = new SendMessage(String.valueOf(123), messageToSend);
        message.setReplyMarkup(messageService.generateMenuKeyBoard("Test"));


        SendMessage actualMessage = messageService.sendReplyMessage(123, messageToSend,
                messageService.generateMenuKeyBoard("Test"));
        assertThat(actualMessage).isNotNull();
        assertThat(actualMessage.getText()).isEqualTo(message.getText());
        assertThat(actualMessage.getChatId()).isEqualTo(message.getChatId());
        assertThat(actualMessage.getReplyMarkup()).isEqualTo(message.getReplyMarkup());
        assertThat(actualMessage).isEqualTo(message);
    }

    @Test
    public void generateMenuKeyBoardTest() {
        ReplyKeyboardMarkup menuKeyBoard = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Test");
        keyboardRows.add(row);
        menuKeyBoard.setKeyboard(keyboardRows);

        ReplyKeyboardMarkup actualMenuKeyBoard = messageService.generateMenuKeyBoard("Test", "Test2");

        assertThat(actualMenuKeyBoard).isNotNull();
        assertThat(actualMenuKeyBoard).isNotEqualTo(menuKeyBoard);

        keyboardRows = new ArrayList<>();
        row = new KeyboardRow();
        List<String> buttons = new ArrayList<>();
        buttons.add("Test");
        buttons.add("Test2");
        row.addAll(buttons);
        keyboardRows.add(row);
        menuKeyBoard.setKeyboard(keyboardRows);
        menuKeyBoard.setResizeKeyboard(true);
        menuKeyBoard.setSelective(true);
        menuKeyBoard.setInputFieldPlaceholder("ВЫБЕРИ ПУНКТ ИЗ МЕНЮ))");

        assertThat(actualMenuKeyBoard.getSelective()).isTrue();
        assertThat(actualMenuKeyBoard.getInputFieldPlaceholder()).isEqualTo("ВЫБЕРИ ПУНКТ ИЗ МЕНЮ))");
        assertThat(actualMenuKeyBoard).isEqualTo(menuKeyBoard);
    }
}
