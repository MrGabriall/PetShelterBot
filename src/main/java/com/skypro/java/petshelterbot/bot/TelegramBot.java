package com.skypro.java.petshelterbot.bot;

import com.skypro.java.petshelterbot.config.BotConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static com.skypro.java.petshelterbot.command.BotCommands.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
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

        if (update.hasMessage() && update.getMessage().hasText()) {

            String commandText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String userName = update.getMessage().getChat().getUserName();

            switch (commandText) {
                case START:
                    startCommandReceived(chatId, userName);
                    break;
                case INFO:
                    sendMessage(chatId, "BotMessages.INFO_MESSAGE");
                    break;
                case HOW_TO_ADOPT:
                    sendMessage(chatId, "BotMessages.HOW_TO_ADOPT_MESSAGE");
                    break;
                case SEND_REPORT:
                    sendMessage(chatId, "BotMessages.SEND_REPORT_MESSAGE");
                case CALL_VOLUNTEER:
                    sendMessage(chatId, "BotMessages.CALL_VOLUNTEER_MESSAGE");
                    break;
                default:
                    sendMessage(chatId, UNKNOWN_COMMAND);
            }
        }
    }

    /**
     * Send Welcome message
     * @param chatId value from update
     * @param name userName value from update
     */
    private void startCommandReceived(long chatId, String name) {
        String messageToSend = "Привет, " + name + "! BotMessages.WELCOME_MESSAGE";
        sendMessage(chatId, messageToSend);
    }

    /**
     * Send any message
     * @param chatId value from update
     * @param messageToSend message from BotMessages
     */
    private void sendMessage(long chatId, String messageToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(messageToSend);

        executeMessage(message);
    }

    /**
     * Calls execute(message) in the try block
     * If an exception is caught, it writes the error to the logs
     * @param message should be executed
     */
    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            //LOGGER.error(ERROR_TEXT + e.getMessage());
        }
    }
}
