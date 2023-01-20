package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.TelegramBot;
import com.skypro.java.petshelterbot.entity.*;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import com.skypro.java.petshelterbot.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.skypro.java.petshelterbot.message.BotOutMessages.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author EosReign
 */

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private TelegramBot telegramBot;

    @Mock
    private SendMessage sendMessage;

    @InjectMocks
    private ReportService reportService;

    @Test
    public void getReportPositive() {
        Photo photo1 = new Photo();
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2022, 5, 23),
                                                  LocalTime.of(23, 43, 25, 0));
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        Report report1 = new Report(dateTime, pet1, owner1, "Сухой корм",
                    "Состояние здоровье: удовлетворительное",
                     "Отсутствие поведенческих изменений", photo1, true);

        Mockito.when(reportRepository.getReportById(0L)).thenReturn(report1);
        assertEquals(reportService.getReport(0L), report1);
        assertNotNull(reportService.getReport(0L));
    }

    @Test
    public void getReportNegative() {
        Mockito.when(reportRepository.getReportById(0L)).thenReturn(null);
        assertNull(reportService.getReport(0L));
    }

    @Test
    public void approveOwnerPositive() {
        Volunteer volunteer1 = new Volunteer(0L, "Main", "Man");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353", 20, volunteer1, pet1);
        Mockito.when(ownerRepository.getOwnerById(0L)).thenReturn(owner1);
        assertEquals(reportService.approveOwner(0L), owner1);
        assertNotNull(reportService.approveOwner(0L));
    }

    @Test
    public void approveOwnerNegative() {
        Mockito.when(ownerRepository.getOwnerById(0L)).thenReturn(null);
        assertNull(reportService.getReport(0L));
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

}
