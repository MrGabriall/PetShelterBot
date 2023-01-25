package com.skypro.java.petshelterbot.bot.botapi.handler;

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
import static com.skypro.java.petshelterbot.message.BotCommands.TO_MAIN_MENU;
import static com.skypro.java.petshelterbot.message.BotOutMessages.*;

@ExtendWith(MockitoExtension.class)
public class HowToAdoptStateHandlerTest {
    @Mock
    private MessageService messageService;

    @InjectMocks
    private HowToAdoptStateHandler howToAdoptStateHandler;

    @Test
    public void handleHowToAdoptTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(HOW_TO_ADOPT);
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendReplyMessage(1L, POTENTIAL_ANIMAL_OWNER_INFO_HELLO, messageService
                .generateMenuKeyBoard(
                        RULES_BEFORE_ADOPTING,
                        REQUIRED_DOCUMENTS,
                        PET_TRANSPORTATION,
                        PUPPY_HOUSE,
                        ADULT_DOG_HOUSE,
                        DOG_HANDLERS_TIPS,
                        DOG_HANDLERS_LIST,
                        WHY_REJECTED,
                        CALL_VOLUNTEER,
                        TO_MAIN_MENU
                ));
    }

    @Test
    public void handleRulesBeforeAdoptingTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(RULES_BEFORE_ADOPTING);
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, POTENTIAL_ANIMAL_OWNER_REGULATIONS_ANIMAL);
    }

    @Test
    public void handleRequiredDocumentsTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(REQUIRED_DOCUMENTS);
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, POTENTIAL_ANIMAL_OWNER_LIST_THE_DOCUMENTS);
    }

    @Test
    public void handlePetTransportationTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(PET_TRANSPORTATION);
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, POTENTIAL_ANIMAL_OWNER_RECOMMENDATIONS_TRANSPORT);
    }

    @Test
    public void handlePuppyHouseTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(PUPPY_HOUSE);
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, POTENTIAL_ANIMAL_OWNER_LIST_RECOMMENDATIONS_HOME);
    }

    @Test
    public void handleAdultDogHouseTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(ADULT_DOG_HOUSE);
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, POTENTIAL_ANIMAL_OWNER_LIST_RECOMMENDATIONS_HOME_BIG_DOG);
    }

    @Test
    public void handleDogHandlersTipsTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(DOG_HANDLERS_TIPS);
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, POTENTIAL_ANIMAL_OWNER_ADVICE_COMMUNICATION_ANIMAL);
    }

    @Test
    public void handleDogHandlersListTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(DOG_HANDLERS_LIST);
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, POTENTIAL_ANIMAL_OWNER_RECOMMENDATIONS_ON_CYNOLOGIST);
    }

    @Test
    public void handleWhyRejectedTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(WHY_REJECTED);
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, POTENTIAL_ANIMAL_OWNER_RENOUNCEMENT);
    }

    @Test
    public void handleSendContactsTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(SEND_CONTACTS);
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, "\uD83D\uDEE0 Button is under maintenance");
    }

    @Test
    public void handleTestButtonTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText("Test Button");
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, "Test text for for CAT SHELTER");
    }

    @Test
    public void handleUnknownButtonTest() {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText("");
        howToAdoptStateHandler.handle(message);
        Mockito.verify(messageService).sendMessage(1L, UNKNOWN_COMMAND);
    }

}
