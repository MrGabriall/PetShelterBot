package com.skypro.java.petshelterbot.bot.botapi.handler;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.bot.botapi.InputMessageHandler;
import com.skypro.java.petshelterbot.entity.UserState;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import com.skypro.java.petshelterbot.repository.VolunteerRepository;
import com.skypro.java.petshelterbot.service.MessageService;
import com.skypro.java.petshelterbot.service.UserStateService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


/**
 * Default state handler
 *
 * @author evnag
 */
@Component
public class DefaultMessageHandler implements InputMessageHandler {

    private final MessageService messageService;
    private final UserStateService userStateService;
    private final OwnerRepository ownerRepository;
    private final VolunteerRepository volunteerRepository;

    public DefaultMessageHandler(MessageService messageService, UserStateService userStateService, OwnerRepository ownerRepository, VolunteerRepository volunteerRepository) {
        this.messageService = messageService;
        this.userStateService = userStateService;
        this.ownerRepository = ownerRepository;
        this.volunteerRepository = volunteerRepository;
    }


    /**
     * Processes a message in the context of a specific state
     *
     * @param message from update
     * @return SendMessage
     */
    @Override
    public SendMessage handle(Message message) {
        return processInputMessage(message);
    }

    /**
     * Gets the handler name
     *
     * @return BotState
     */
    @Override
    public BotState getHandler() {
        return BotState.START_STATE;
    }

    /**
     * Processes the user's text message.
     * Send Welcome message w/keyboard depending on the user's status
     *
     * @param message from update
     * @return {@link SendMessage}
     */
    private SendMessage processInputMessage(Message message) {
        //TODO: Вынести проверку isOwner в BotStateContext?!
        long chatId = message.getChatId();
        String userName = message.getChat().getUserName();
        if (isOwner(message)) {
            userStateService.setBotState(chatId, BotState.OWNER_STATE);
            return messageService.startCommandFromOwner(chatId, userName);
        } else if (isVolunteer(message)) {
            userStateService.setBotState(chatId, BotState.VOLUNTEER_STATE);
            return messageService.sendMessage(chatId, "Я пользователь мне нужна помощь");//TODO: что приходит волонтеру?
        }
        userStateService.setBotState(chatId, BotState.CHOOSE_SHELTER_STATE);
        return messageService.startCommandFromDefault(chatId, userName);
    }

    /**
     * Checks whether the user is the owner
     *
     * @param message from update
     * @return boolean
     */
    private boolean isOwner(Message message) {
        UserState userState = userStateService.getUserState(message);
        return ownerRepository.findAll().stream()
                .anyMatch(o -> o.getChatId().equals(userState.getChatId()));
    }

    private boolean isVolunteer(Message message) {
        UserState userState = userStateService.getUserState(message);
        return volunteerRepository.getAll().stream()
                .anyMatch(v -> v.getChatId().equals(userState.getChatId()));
    }
}
