package com.skypro.java.petshelterbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.skypro.java.petshelterbot.command.BotCommands.*;
import static com.skypro.java.petshelterbot.message.BotOutMessages.*;

@Service
public class UpdateService {

    private final MessageService messageService;
    private final OwnerReportService reportService;

    public UpdateService(MessageService messageService, OwnerReportService reportService) {
        this.messageService = messageService;
        this.reportService = reportService;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage messageToSend = null;
        if (update.hasMessage() && update.getMessage().hasText()) {
            messageToSend = handleInputMessage(update.getMessage());
        }
        if(update.hasMessage() && update.getMessage().hasPhoto() && update.getMessage().getCaption() != null) {
            messageToSend = reportService.saveReport(update);
        }
        return messageToSend;
    }

    private SendMessage handleInputMessage(Message message) {
        String commandText = message.getText();
        long chatId = message.getChatId();
        String userName = message.getChat().getUserName();
        SendMessage messageToSend;

        switch (commandText) {
            case START, TO_MAIN_MENU -> messageToSend = messageService.startCommandReceived(chatId, userName);
            case INFO ->
                    messageToSend = messageService.sendReplyMessage(chatId, SELECT_OPTION, messageService.generateMenuKeyBoard(
                            ABOUT_SHELTER,
                            SHELTER_ADDRESS,
                            SAFETY_MEASURES,
                            SEND_CONTACTS,
                            CALL_VOLUNTEER,
                            TO_MAIN_MENU
                    ));
            case ABOUT_SHELTER -> messageToSend = messageService.sendMessage(chatId, NEW_USER_INFO_START);
            case SHELTER_ADDRESS -> messageToSend = messageService.sendMessage(chatId, NEW_USER_INFO_SHELTER);
            case SAFETY_MEASURES -> messageToSend = messageService.sendMessage(chatId, NEW_USER_INFO_REGULATIONS);

            case HOW_TO_ADOPT ->
                    messageToSend = messageService.sendReplyMessage(chatId, POTENTIAL_ANIMAL_OWNER_INFO_HELLO, messageService.generateMenuKeyBoard(
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
            case RULES_BEFORE_ADOPTING ->
                    messageToSend = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_REGULATIONS_ANIMAL);
            case REQUIRED_DOCUMENTS ->
                    messageToSend = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_LIST_THE_DOCUMENTS);
            case PET_TRANSPORTATION ->
                    messageToSend = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_RECOMMENDATIONS_TRANSPORT);
            case PUPPY_HOUSE ->
                    messageToSend = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_LIST_RECOMMENDATIONS_HOME);
            case ADULT_DOG_HOUSE ->
                    messageToSend = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_LIST_RECOMMENDATIONS_HOME_BIG_DOG);
            case DOG_HANDLERS_TIPS ->
                    messageToSend = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_ADVICE_COMMUNICATION_ANIMAL);
            case DOG_HANDLERS_LIST ->
                    messageToSend = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_RECOMMENDATIONS_ON_CYNOLOGIST);
            case WHY_REJECTED ->
                    messageToSend = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_RENOUNCEMENT);

            case SEND_CONTACTS ->
                    messageToSend = messageService.sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_NEW_CONTACT);

            case SEND_REPORT -> messageToSend = messageService.sendMessage(chatId, "BotMessages.SEND_REPORT_MESSAGE");
            case CALL_VOLUNTEER ->
                    messageToSend = messageService.sendMessage(chatId, "BotMessages.CALL_VOLUNTEER_MESSAGE");
            default -> messageToSend = messageService.sendMessage(chatId, UNKNOWN_COMMAND);
        }
        return messageToSend;
    }

}
