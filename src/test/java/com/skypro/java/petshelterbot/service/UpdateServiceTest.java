package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.botapi.BotStateContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

import static com.skypro.java.petshelterbot.message.BotCommands.CALL_VOLUNTEER;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author evnag
 */

@ExtendWith(MockitoExtension.class)
public class UpdateServiceTest {

    @Mock
    private UserStateService userStateService;

    @Mock
    private BotStateContext botStateContext;

    @InjectMocks
    private UpdateService updateService;


    @Test
    public void handleUpdateTest() {
        SendMessage actual = new SendMessage(String.valueOf(1L), CALL_VOLUNTEER);


        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setText(CALL_VOLUNTEER);
        message.setChat(chat);
        Update update = new Update();
        update.setMessage(message);

        assertThat(actual).isEqualTo(updateService.handleUpdate(update));
        List<PhotoSize> photoSizeList = new ArrayList<>();
        message.setPhoto(photoSizeList);

    }
//
//    @Test
//    public void handleInputMessageTest() {
//
//    }
//
//    @Test
//    public void handleInputPhoto() {
//
//    }
}
