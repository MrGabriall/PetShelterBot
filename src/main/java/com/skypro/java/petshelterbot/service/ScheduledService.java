package com.skypro.java.petshelterbot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledService {
    private final ReportService reportService;

    private static final String oneDayMessageWarning = "Дорогой усыновитель, мы заметили, " +
            "что ты заполняешь отчет не так подробно, как необходимо. " +
            "Пожалуйста, подойди ответственнее к этому занятию. " +
            "В противном случае, волонтеры приюта будут " +
            "обязаны самолично проверять условия содержания животного";

    public ScheduledService(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * This method everyday check in the database the IDs of owners who doesn't send report yesterday and two days before.
     * And this method send notifications to owners using {@link ReportService#sendMessage(Long, String)}
     * And push list owners to volunteer using {}
     */
    @Scheduled(cron = "0 0 9 * * *")
    public void everydayReportsCheck() {
        List<Long> twoDaysAgoIdsList = reportService.reportsChecker(2);
        List<Long> yesterdayIdsList = reportService.reportsChecker(1);
        yesterdayIdsList.removeAll(twoDaysAgoIdsList);
        reportService.getOwnerChatIdsByIds(yesterdayIdsList)
                .forEach(id -> reportService.sendMessage(id, oneDayMessageWarning));

        reportService.pushVolunteersByOwnerIds(twoDaysAgoIdsList);
    }

    public void everydayDecrementTrialPeriod() {
        /*  @TODO
            Написать реализацию и добавить декремент поля в бд
         */
    }
}
