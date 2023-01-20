package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.TelegramBot;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledService {

    private final TelegramBot telegramBot;
    private final ReportService reportService;

    private static final String oneDayMessageWarning = "Дорогой усыновитель, мы заметили, " +
            "что ты заполняешь отчет не так подробно, как необходимо. " +
            "Пожалуйста, подойди ответственнее к этому занятию. " +
            "В противном случае, волонтеры приюта будут " +
            "обязаны самолично проверять условия содержания животного";
    public ScheduledService(TelegramBot telegramBot, ReportService reportService) {
        this.telegramBot = telegramBot;
        this.reportService = reportService;
    }

    /**
     * This method everyday check in the database the IDs of owners who doesn't send report yesterday and two days before.
     * And this method send notifications to owners using {@link ScheduledService#sendMessage(Long)}
     * And push list owners to volunteer using {}
     */
    @Scheduled(cron = "0 0 8 ? * * *")
    public void everydayReportsCheck() {
        List<Long> twoDaysAgoIdsList = reportService.reportsChecker(2);
        List<Long> yesterdayIdsList = reportService.reportsChecker(1);

        List<Long> warningList = new ArrayList<>(twoDaysAgoIdsList);
        warningList.retainAll(yesterdayIdsList);

        yesterdayIdsList.removeAll(warningList);
        yesterdayIdsList.forEach(this::sendMessage);

        //@TODO
        //Make push warning list to volunteers
    }

    @Scheduled
    public void checkTrialPeriod() {
        /*  @TODO
            Написать реализацию и добавить декремент поля в бд
         */
    }

    /**
     * This method send notification message to owners who forgot send report yesterday
     * @param id
     */
    private void sendMessage(Long id){
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(oneDayMessageWarning);
        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            //logger
        }
    }
}
