package com.skypro.java.petshelterbot.bot;

import com.skypro.java.petshelterbot.config.BotConfig;
import com.skypro.java.petshelterbot.repository.VolunteerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

import static com.skypro.java.petshelterbot.command.BotCommands.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final VolunteerRepository volunteerRepository;
    private final BotConfig config;


    public TelegramBot(BotConfig config, VolunteerRepository volunteerRepository) {
        this.config = config;

        this.volunteerRepository = volunteerRepository;
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

            System.err.println("chatId = [" + update.getMessage().getChatId() + "]");


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
                    break;
                case CALL_VOLUNTEER:
                    sendMessage(chatId, "BotMessages.CALL_VOLUNTEER_MESSAGE");
                    break;
                default:
                    sendMessage(chatId, UNKNOWN_COMMAND);
                    /////////////////////////////////////////////////////
                    if (commandText.equals("/volunteer")) {
                        SendMessage newMess = new SendMessage();
                        newMess.setChatId(chatId);
                        newMess.setText("вызываю волонтёра");
                        executeMessage(newMess);

                        volunteerCall(chatId, stringVolunteerShowId());


                    }
                    /////////////////////////////////////////////////////
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
        sendReplyMessage(chatId, messageToSend, generateReplyKeyBoard());
    }

    /**
     * Send any message
     * @param chatId value from update
     * @param messageToSend message from BotMessages
     */
    private void sendMessage(long chatId, String messageToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);//вопрос зачем приводить
        message.setText(messageToSend);
        executeMessage(message);
    }

    /**
     * Send any message w/ reply markup keyboard
     * @param chatId value from update
     * @param messageToSend message from BotMessages
     * @param keyboardMarkup selected keyboard
     */
    private void sendReplyMessage(long chatId, String messageToSend, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(messageToSend);
        message.setReplyMarkup(keyboardMarkup);

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

    /**
     * Generate menu w/ 2 rows of 2 buttons
     * @return ReplyKeyboardMarkup
     */
    private ReplyKeyboardMarkup generateReplyKeyBoard() {

        ReplyKeyboardMarkup startKeyBoard = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        row1.add(INFO);
        row1.add(HOW_TO_ADOPT);
        keyboardRows.add(row1);

        row2.add(CALL_VOLUNTEER);
        row2.add(SEND_REPORT);
        keyboardRows.add(row2);

        startKeyBoard.setKeyboard(keyboardRows);
        startKeyBoard.setResizeKeyboard(true);

        return startKeyBoard;
    }


    /**
     * Test
     * метод волонтёр
     *
     * @param idChat
     * @param volunteerShowId нужно удалить
     */
    private void volunteerCall(long idChat, long volunteerShowId) {
        SendMessage newMess = new SendMessage();
        newMess.setChatId(idChat);
        newMess.setText("[ вызов метода волонтёр ] я волонтёр слушаю вас моё id = " + volunteerShowId);
        executeMessage(newMess);
        System.out.println(" Вызов метода volunteerCall = " + volunteerShowId);

    }

    ///////////////////////////////////////////////

    /**
     * метод списка user_id
     */
    private long stringVolunteerShowId() {
        long index = 0;
        String message = null;
        for (String s : volunteerRepository.stringVolunteerId()) {
            message = s;
            index = Long.valueOf(s);
            System.out.println("index = [" + index + "]");
        }
        System.err.println("message test = " + message);
        return index;

    }

}
