package com.skypro.java.petshelterbot.bot;

import com.skypro.java.petshelterbot.config.BotConfig;
import com.skypro.java.petshelterbot.service.ReportService;
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
import static com.skypro.java.petshelterbot.message.BotOutMessages.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private final ReportService reportService;


    public TelegramBot(BotConfig config, ReportService reportService) {
        this.config = config;
        this.reportService = reportService;
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
                case START, TO_MAIN_MENU -> startCommandReceived(chatId, userName);
                case INFO -> sendReplyMessage(chatId, SELECT_OPTION, generateMenuKeyBoard(
                        ABOUT_SHELTER,
                        SHELTER_ADDRESS,
                        SAFETY_MEASURES,
                        SEND_CONTACTS,
                        CALL_VOLUNTEER,
                        TO_MAIN_MENU
                ));
                case ABOUT_SHELTER -> sendMessage(chatId, NEW_USER_INFO_START);
                case SHELTER_ADDRESS -> sendMessage(chatId, NEW_USER_INFO_SHELTER);
                case SAFETY_MEASURES -> sendMessage(chatId, NEW_USER_INFO_REGULATIONS);

                case HOW_TO_ADOPT -> sendReplyMessage(chatId, POTENTIAL_ANIMAL_OWNER_INFO_HELLO, generateMenuKeyBoard(
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
                case RULES_BEFORE_ADOPTING -> sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_REGULATIONS_ANIMAL);
                case REQUIRED_DOCUMENTS -> sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_LIST_THE_DOCUMENTS);
                case PET_TRANSPORTATION ->
                        sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_RECOMMENDATIONS_TRANSPORT);
                case PUPPY_HOUSE ->
                        sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_LIST_RECOMMENDATIONS_HOME);
                case ADULT_DOG_HOUSE ->
                        sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_LIST_RECOMMENDATIONS_HOME_BIG_DOG);
                case DOG_HANDLERS_TIPS ->
                        sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_ADVICE_COMMUNICATION_ANIMAL);
                case DOG_HANDLERS_LIST ->
                        sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_RECOMMENDATIONS_ON_CYNOLOGIST);
                case WHY_REJECTED -> sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_RENOUNCEMENT);

                case SEND_CONTACTS -> sendMessage(chatId, POTENTIAL_ANIMAL_OWNER_NEW_CONTACT);

                case SEND_REPORT -> sendMessage(chatId, "BotMessages.SEND_REPORT_MESSAGE");
                case CALL_VOLUNTEER -> sendMessage(chatId, "BotMessages.CALL_VOLUNTEER_MESSAGE");
                default -> sendMessage(chatId, UNKNOWN_COMMAND);
            }
        }
        /**
         * nadillustrator
         * Checks if the Message contains Photo+Caption and passes the update to the ReportService to process the report.
         */
        if(update.hasMessage() && update.getMessage().hasPhoto() && update.getMessage().getCaption() != null) {
            reportService.saveReport(update);
        }
    }

    /**
     * Send Welcome message
     *
     * @param chatId value from update
     * @param name   userName value from update
     */
    private void startCommandReceived(long chatId, String name) {
        String messageToSend = "Привет, " + name + "! " + NEW_USER_HELLO;
        sendReplyMessage(chatId, messageToSend, generateMenuKeyBoard(INFO, HOW_TO_ADOPT, CALL_VOLUNTEER, SEND_REPORT));
    }

    /**
     * Send any message
     *
     * @param chatId        value from update
     * @param messageToSend message from BotMessages
     */
    private void sendMessage(long chatId, String messageToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(messageToSend);

        executeMessage(message);
    }

    /**
     * Send any message w/ reply markup keyboard
     *
     * @param chatId         value from update
     * @param messageToSend  message from BotMessages
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
     *
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
     * Generates a reply keyboard from String varargs
     *
     * @param buttons String values with text for menu buttons, @NotNull
     * @return ReplyKeyboardMarkup - menu keyboard
     */
    private ReplyKeyboardMarkup generateMenuKeyBoard(String... buttons) {

        ReplyKeyboardMarkup menuKeyBoard = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (String button : buttons) {
            row.add(button);
            if (row.size() == 1) {
                keyboardRows.add(row);
            } else if (row.size() > 1) {
                row = new KeyboardRow();
            }
        }

        menuKeyBoard.setKeyboard(keyboardRows);
        menuKeyBoard.setResizeKeyboard(true);
        menuKeyBoard.setSelective(true);
        return menuKeyBoard;
    }
}
