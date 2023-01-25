package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.TelegramBot;
import com.skypro.java.petshelterbot.dto.ReportDto;
import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Report;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import com.skypro.java.petshelterbot.repository.ReportRepository;
import org.apache.el.stream.Stream;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private final PhotoSaverService photoSaverService;

    public ReportService(ReportRepository reportRepository,
                         OwnerRepository ownerRepository,
                         TelegramBot telegramBot, PhotoSaverService photoSaverService) {
        this.reportRepository = reportRepository;
        this.ownerRepository = ownerRepository;
        this.telegramBot = telegramBot;
        this.photoSaverService = photoSaverService;
    }

    /**
     * This method returns a report by reportId
     *
     * @param reportId
     * @return ReportDto
     */
    public ReportDto getReport(Long reportId) {
        try {
            Report report = reportRepository.getReportById(reportId);
            return fromReportToReportDto(report);
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
     * @return ReportDto
     */
    public ReportDto markReportAsCorrectById(Long reportId) {
        try {
            Report report = reportRepository.findById(reportId).get();
            report.setCorrect(true);
            return fromReportToReportDto(reportRepository.save(report));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method marks the report as incorrectly completed putting a boolean false in a field isCorrect
     *
     * @param reportId
     * @return ReportDto
     */
    public ReportDto markReportsAsIncorrectById(Long reportId) {
        try {
            Report report = reportRepository.findById(reportId).get();
            report.setCorrect(false);
            return fromReportToReportDto(reportRepository.save(report));
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
     * @param ownerId
     * @return List<ReportDto>
     */
    //TODO проверить имена всех переменных в параметрах на соответствие (в этом была ошибка)
    public List<ReportDto> getAllReportsByOwnerId(Long ownerId) {
        try {
            // reportRepository.findAllByOwnerId(ownerId).forEach(r->photoSaverService.readPhotoFromTelegram(r.getPhoto().getId()));
            List<Report> reports = reportRepository.findAllByOwnerId(ownerId);
            return reports.stream().map(r -> fromReportToReportDto(r)).collect(Collectors.toList());

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method returns ALL reports for a specific owner by name and surname
     *
     * @param firstName
     * @param lastName
     * @return List<ReportDto>
     */
    public List<ReportDto> getAllReportsByOwnerName(String firstName, String lastName) {
        try {
            List<Report> reports = reportRepository.findAllByOwnerFirstNameAndOwnerLastName(firstName, lastName);
            return reports.stream().map(r -> fromReportToReportDto(r)).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method returns ALL unchecked reports for a specific owner by ID
     *
     * @param reportId
     * @return List<ReportDto>
     */
    public List<ReportDto> getAllUncheckedReportsByOwnerId(Long reportId) {
        try {
            List<Report> reports = reportRepository.findAllByOwnerIdAndCorrectIsNull(reportId);
            return reports.stream().map(r -> fromReportToReportDto(r)).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method returns ALL unchecked reports for a specific owner by name and surname
     *
     * @param firstName
     * @param lastName
     * @return List<ReportDto>
     */
    public List<ReportDto> getAllUncheckedReportsByOwnerName(String firstName, String lastName) {
        try {
            List<Report> reports = reportRepository
                    .findAllByOwnerFirstNameAndOwnerLastNameAndCorrectIsNull(firstName, lastName);
            return  reports.stream().map(r -> fromReportToReportDto(r)).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method returns all reports from the database
     * @return List<ReportDto>
     */
    public List<ReportDto> getAllUncheckedReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(r->fromReportToReportDto(r)).collect(Collectors.toList());
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

    /**
     * This method send notification message to owners who forgot send report yesterday
     *
     * @param id
     * @param text
     */
    void sendMessage(Long id, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(text);
        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            //logger
        }
    }

    public ReportDto fromReportToReportDto(Report report) {
        String photoLink = "There will be method getPhotoLink"; //TODO write method for photo
        return new ReportDto(report.getId(),
                report.getIncomingReportDate(),
                report.getPet().getName(),
                report.getOwner().getFirstName() + " " + report.getOwner().getLastName(),
                report.getPetDiet(),
                report.getHealthAndCondition(),
                report.getBehavioralChanges(),

                photoLink);
    }
}
