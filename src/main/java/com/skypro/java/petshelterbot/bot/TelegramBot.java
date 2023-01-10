package com.skypro.java.petshelterbot.bot;

import com.skypro.java.petshelterbot.config.BotConfig;
import com.skypro.java.petshelterbot.service.UpdateService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private final UpdateService updateService;

    public TelegramBot(BotConfig config, UpdateService updateService) {
        this.config = config;
        this.updateService = updateService;
    }

    @PostConstruct
    public void addBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        try {
            execute(updateService.handleUpdate(update));
        } catch (TelegramApiException e) {
            //LOGGER.error(ERROR_TEXT + e.getMessage());
        }
    }

}
