package com.skypro.java.petshelterbot.bot.botapi.handler;

import com.skypro.java.petshelterbot.message.BotOutMessages;
import com.skypro.java.petshelterbot.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.skypro.java.petshelterbot.message.BotCommands.*;
import static com.skypro.java.petshelterbot.message.BotOutMessages.*;

@ExtendWith(MockitoExtension.class)
public class InfoStateHandlerTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private InfoStateHandler infoStateHandler;

    @Test
    public void handleInfoTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(INFO);
        infoStateHandler.handle(message);
        Mockito.verify(messageService).sendReplyMessage(1L, SELECT_OPTION,
                messageService.generateMenuKeyBoard(
                        ABOUT_SHELTER,
                        SHELTER_ADDRESS,
                        SAFETY_MEASURES,
                        CALL_VOLUNTEER,
                        TO_MAIN_MENU
                ));
    }

    @Test
    public void handleAboutShelterTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(ABOUT_SHELTER);
        infoStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, NEW_USER_INFO_START);
    }

    @Test
    public void handleShelterAddressTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(SHELTER_ADDRESS);
        infoStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, NEW_USER_INFO_SHELTER);
    }

    @Test
    public void handleSafetyMeasuresTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(SAFETY_MEASURES);
        infoStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, NEW_USER_INFO_REGULATIONS);
    }

    @Test
    public void handleUnknown_CommandTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText("");
        infoStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, UNKNOWN_COMMAND);
    }


}
