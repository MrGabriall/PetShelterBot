package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.entity.UserState;
import com.skypro.java.petshelterbot.repository.UserStateRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Defines methods for processing user states
 *
 * @author evnag
 */

@Service
public class UserStateService {
    private final UserStateRepository userStateRepository;

    public UserStateService(UserStateRepository userStateRepository) {
        this.userStateRepository = userStateRepository;
    }

    /**
     * Returns an entity from the database by chat id
     *
     * @param message value from the update
     * @return UserState
     */
    public UserState getUserState(Message message) {
        return userStateRepository.findByChatId(message.getChatId());
    }

    /**
     * Sets the state for the userState by id
     *
     * @param chatId   unique chat id
     * @param botState state to be set
     */
    public void setBotState(Long chatId, BotState botState) {
        UserState userState = userStateRepository.findByChatId(chatId);
        if (userState == null) { //TODO: вынести проверку+создание новой сущности в отдельный метод
            UserState newUserState = new UserState(chatId, botState);
            userStateRepository.save(newUserState);
        } else {
            userState.setState(botState);
            userStateRepository.save(userState);
        }
    }

}
