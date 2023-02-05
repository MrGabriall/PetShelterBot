package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.entity.UserState;
import com.skypro.java.petshelterbot.repository.UserStateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author evnag
 */
@ExtendWith(MockitoExtension.class)
public class UserStateServiceTest {

    @Mock
    private UserStateRepository userStateRepository;

    @InjectMocks
    private UserStateService userStateService;

    @Test
    public void getUserStateTest(){
        UserState userStateMock = new UserState();
        userStateMock.setChatId(1L);
        userStateMock.setState(BotState.START_STATE);

        doReturn(userStateMock).when(userStateRepository).findByChatId(userStateMock.getChatId());
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        UserState userState = userStateService.getUserState(message);
        userState.setState(BotState.START_STATE);
        assertThat(userStateMock.getState()).isEqualTo(userState.getState());
        assertThat(userStateMock.getChatId()).isEqualTo(userState.getChatId());
    }

    @Test
    public void setBotStateTest() {
        UserState userStateMock = new UserState();
        userStateMock.setState(BotState.START_STATE);
        userStateMock.setChatId(123L);

        UserState userState = new UserState();
        userState.setChatId(1L);
        userState.setState(BotState.START_STATE);
        assertThat(userStateMock.getState()).isEqualTo(userState.getState());

        doReturn(userStateMock).when(userStateRepository).findByChatId(123L);
        BotState newState = BotState.INFO_STATE;
        userStateService.setBotState(123L, newState);
        assertThat(userStateMock.getState()).isNotEqualTo(userState.getState());
        assertThat(userStateMock.getState()).isEqualTo(newState);
    }

    @Test
    public void setBotStateNegativeTest() {
        UserState userStateMock = new UserState();
        UserState newUserState = new UserState(1L, BotState.INFO_STATE);

        doReturn(userStateMock).when(userStateRepository).findByChatId(1L);
        userStateRepository.delete(userStateMock);

        userStateService.setBotState(1L, BotState.INFO_STATE);
        userStateMock.setChatId(1L);
        assertThat(userStateMock).isEqualTo(newUserState);
    }
}
