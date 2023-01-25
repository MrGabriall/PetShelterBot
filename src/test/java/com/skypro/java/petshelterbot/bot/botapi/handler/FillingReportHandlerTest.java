package com.skypro.java.petshelterbot.bot.botapi.handler;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.bot.TelegramBot;
import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.entity.UserState;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import com.skypro.java.petshelterbot.repository.PetRepository;
import com.skypro.java.petshelterbot.repository.UserStateRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

@SpringBootTest
public class FillingReportHandlerTest {

    @Autowired
    private FillingReportHandler fillingReportHandler;

    @MockBean
    private UserStateRepository userStateRepository;

    @MockBean
    private OwnerRepository ownerRepository;

    @MockBean
    private PetRepository petRepository;

    @MockBean
    private TelegramBot telegramBot;

    @Test
    public void processUsersMessageTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);

        UserState expected = new UserState(1L, BotState.FILLING_REPORT);
        Pet pet = new Pet();
        pet.setId(1L);
        Owner owner = new Owner();
        owner.setPet(pet);

        Mockito.when(userStateRepository.findByChatId(ArgumentMatchers.eq(chat.getId())))
                .thenReturn(expected);
        Mockito.when(ownerRepository.getOwnerByChatId(ArgumentMatchers.eq(chat.getId())))
                .thenReturn(owner);
        Mockito.when(petRepository.getPetById(ArgumentMatchers.eq(1L)))
                .thenReturn(pet);

        fillingReportHandler.handle(message);

        ArgumentCaptor<UserState> argumentCaptor = ArgumentCaptor.forClass(UserState.class);
        Mockito.verify(userStateRepository, Mockito.times(2)).save(argumentCaptor.capture());
        UserState actual = argumentCaptor.getValue();
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
