package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.TelegramBot;
import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Report;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import com.skypro.java.petshelterbot.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.skypro.java.petshelterbot.message.BotOutMessages.*;

/**
 * This class is responsible for handling requests coming from the ReportController from the side of the volunteer.
 *
 * @author nadillustrator
 */
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final OwnerRepository ownerRepository;
    private final TelegramBot telegramBot;

    public ReportService(ReportRepository reportRepository,
                         OwnerRepository ownerRepository,
                         TelegramBot telegramBot) {
        this.reportRepository = reportRepository;
        this.ownerRepository = ownerRepository;
        this.telegramBot = telegramBot;
    }

    /**
     * This method returns a report by reportId
     *
     * @param reportId
     * @return Report
     */
    public Report getReport(Long reportId) {
        try {
            return reportRepository.findById(reportId).get();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method sends a message to the user about the successful completion of the trial period using TelegramBot
     *
     * @param ownerId
     * @return Owner
     */
    public Owner approveOwner(Long ownerId) {
        try {
            Owner owner = ownerRepository.findById(ownerId).get();
            Long chatId = owner.getChatId();
            SendMessage sendMessage = new SendMessage(chatId.toString(), PET_MANAGEMENT_FINAL);
            telegramBot.execute(sendMessage);
            return owner;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method sends a message to the user about the unsuccessful completion of the trial period using TelegramBot
     *
     * @param ownerId
     * @return Owner
     */
    public Owner denyOwner(Long ownerId) {
        try {
            Owner owner = ownerRepository.findById(ownerId).get();
            Long chatId = owner.getChatId();
            SendMessage sendMessage = new SendMessage(chatId.toString(), PET_MANAGEMENT_DID_NOT_COPE);
            telegramBot.execute(sendMessage);
            return owner;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method marks the report as correctly completed putting a boolean true in a field isCorrect
     *
     * @param reportId
     * @return Report
     */
    public Report markReportAsCorrectById(Long reportId) {
        try {
            Report report = reportRepository.findById(reportId).get();
            report.setCorrect(true);
            return reportRepository.save(report);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method marks the report as incorrectly completed putting a boolean false in a field isCorrect
     *
     * @param reportId
     * @return Report
     */
    public Report markReportsAsIncorrectById(Long reportId) {
        try {
            Report report = reportRepository.findById(reportId).get();
            report.setCorrect(false);
            return reportRepository.save(report);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method increases the number of days of the probationary period for the owner and
     * sends him a message about the prolongation of the probationary period using TelegramBot
     *
     * @param ownerId
     * @param numberOfDays
     * @return Owner
     */
    public Owner addNumberOfReportDaysByOwnerId(Long ownerId, Integer numberOfDays) {
        try {
            Owner owner = ownerRepository.findById(ownerId).get();
            Long chatId = owner.getChatId();
            owner.setNumberOfReportDays(numberOfDays);
            SendMessage sendMessage = new SendMessage(chatId.toString(),
                    PET_MANAGEMENT_DOP_TIME + numberOfDays + " дней.");
            telegramBot.execute(sendMessage);
            return ownerRepository.save(owner);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method returns ALL reports for a specific owner by ID
     *
     * @param reportId
     * @return List<Report>
     */
    public List<Report> getAllReportsByOwnerId(Long reportId) {
        try {
            return reportRepository.findAllByOwnerId(reportId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method returns ALL reports for a specific owner by name and surname
     *
     * @param firstName
     * @param lastName
     * @return List<Report>
     */
    public List<Report> getAllReportsByOwnerName(String firstName, String lastName) {
        try {
            return reportRepository.findAllByOwnerFirstNameAndOwnerLastName(firstName, lastName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method returns ALL unchecked reports for a specific owner by ID
     *
     * @param reportId
     * @return List<Report>
     */
    public List<Report> getAllUncheckedReportsByOwnerId(Long reportId) {
        try {
            return reportRepository.findAllByOwnerIdAndCorrectIsNull(reportId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method returns ALL unchecked reports for a specific owner by name and surname
     *
     * @param firstName
     * @param lastName
     * @return List<Report>
     */
    public List<Report> getAllUncheckedReportsByOwnerName(String firstName, String lastName) {
        try {
            return reportRepository.findAllByOwnerFirstNameAndOwnerLastNameAndCorrectIsNull(firstName, lastName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method returns all reports from the database
     */
    public List<Report> getAllUncheckedReports() {
        return reportRepository.findAll();
    }


    /**
     * This method returns list of ids that did not send a daily report
     *
     * @return List<Long>
     */
    List<Long> reportsChecker(int daysAgo) {
        List<Long> ids = findAllIdsWithTrial();
        List<Long> warningIds = new ArrayList<>();
        LocalDate currentDate = LocalDate.now().minusDays(daysAgo);
        for (Long id : ids) {
            if (findReportByOwnerIdAndDate(id, currentDate)) {
                warningIds.add(id);
            }
        }
        return warningIds;
    }

    /**
     * This method check yesterday report has arrived
     *
     * @param ownerId
     * @param date
     * @return boolean
     */
    private boolean findReportByOwnerIdAndDate(Long ownerId, LocalDate date) {
        Report report = reportRepository.findReportByOwnerIdAndIncomingReportTime_Date(ownerId, date);
        return report == null;
    }

    /**
     * This method find all ids who have trial period
     *
     * @return List<Long>
     */
    private List<Long> findAllIdsWithTrial() {
        return ownerRepository.findAllByNumberOfReportDaysNotNull().stream()
                .map(Owner::getChatId)
                .toList();
    }
}
