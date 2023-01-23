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
import java.util.ArrayList;
import java.util.List;

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
        LocalDate dateTime = LocalDate.of(2022, 5, 23);
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
        assertNull(reportService.approveOwner(0L));
    }

    /**
     * This method sends a message to the user about the unsuccessful completion of the trial period using TelegramBot
     *
     * parameter ownerId
     * return Owner
     */
    @Test
    public void denyOwner() {
        Photo photo1 = new Photo();
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2022, 5, 23),
                LocalTime.of(23, 43, 25, 0));
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);

        Mockito.when(ownerRepository.getOwnerById(0L)).thenReturn(owner1);
        assertEquals(reportService.denyOwner(0L), owner1);
        assertNotNull(reportService.denyOwner(0L));

    }

    /**
     * This method marks the report as correctly completed putting a boolean true in a field isCorrect
     *
     * parameter reportId
     * return Report
     */
    @Test
    public void markReportAsCorrectById() {
        Photo photo1 = new Photo();
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        LocalDate dateTime = LocalDate.of(2022, 5, 23);
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        Report report1 = new Report(dateTime, pet1, owner1, "Сухой корм",
                "Состояние здоровье: удовлетворительное",
                "Отсутствие поведенческих изменений", photo1, null);
        Report report1Correct = new Report(dateTime, pet1, owner1, "Сухой корм",
                "Состояние здоровье: удовлетворительное",
                "Отсутствие поведенческих изменений", photo1, true);

        Mockito.when(reportRepository.getReportById(report1.getId())).thenReturn(report1);
        Mockito.when(reportRepository.save(report1Correct)).thenReturn(report1Correct);
        assertEquals(report1Correct, reportService.markReportAsCorrectById(report1.getId()));
        assertNotNull(reportService.markReportAsCorrectById(report1.getId()));
    }

    /**
     * This method marks the report as incorrectly completed putting a boolean false in a field isCorrect
     *
     * param reportId
     * return Report
     */
    @Test
    public void markReportsAsIncorrectById() {
        Photo photo1 = new Photo();
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        LocalDate dateTime = LocalDate.of(2022, 5, 23);
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        Report report1 = new Report(dateTime, pet1, owner1, "Сухой корм",
                "Состояние здоровье: удовлетворительное",
                "Отсутствие поведенческих изменений", photo1, null);
        Report report1Uncorrect = new Report(dateTime, pet1, owner1, "Сухой корм",
                "Состояние здоровье: удовлетворительное",
                "Отсутствие поведенческих изменений", photo1, false);

        Mockito.when(reportRepository.getReportById(0L)).thenReturn(report1);
        Mockito.when(reportRepository.save(report1Uncorrect)).thenReturn(report1Uncorrect);
        assertEquals(report1Uncorrect, reportService.markReportsAsIncorrectById(0L));
        assertNotNull(reportService.markReportsAsIncorrectById(0L));

    }

    /**
     * This method increases the number of days of the probationary period for the owner and
     * sends him a message about the prolongation of the probationary period using TelegramBot
     *
     * param ownerId
     * param numberOfDays
     * return Owner
     */
    @Test
    public void addNumberOfReportDaysByOwnerId() {
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2022, 5, 23),
                LocalTime.of(23, 43, 25, 0));
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        Owner owner1New = new Owner(0L, "sheesh", "comrade", "+79245342353",
                30, volunteer1, pet1);


        Mockito.when(ownerRepository.getOwnerById(0L)).thenReturn(owner1);
        Mockito.when(ownerRepository.save(owner1New)).thenReturn(owner1New);
        assertEquals(owner1New, reportService.addNumberOfReportDaysByOwnerId(0L, 30));
        assertNotNull(reportService.addNumberOfReportDaysByOwnerId(0L, 30));

    }
    /**
     * This method returns ALL reports for a specific owner by ID
     *
     * param ownerId
     * return List<Report>
     */
    @Test
    public void getAllReportsByOwnerId() {
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        Pet pet2 = new Pet("cat", LocalDate.of(2022, 5, 23));
        Pet pet3 = new Pet("snake", LocalDate.of(2022, 8, 5));
        LocalDate dateTime = LocalDate.of(2022, 5, 23);
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        Report report1 = new Report(dateTime, pet1, owner1, "Сухой корм",
                "Состояние здоровье: удовлетворительное",
                "Отсутствие поведенческих изменений", null, true);
        Report report2 = new Report(dateTime, pet2, owner1, "Сухой корм",
                "Состояние здоровье: Восхитительное",
                "Радуется как не в себя", null, true);
        Report report3 = new Report(dateTime, pet3, owner1, "Сухой корм",
                "Состояние здоровье: Плачевное",
                "Угнетенное состояние здоровья", null, true);
        List<Report> arrReports = new ArrayList<>();

        Mockito.when(reportRepository.findAllByOwnerId(0L)).thenReturn(arrReports);
        assertEquals(arrReports, reportService.getAllReportsByOwnerId(0L));
        assertNotNull(reportService.getAllReportsByOwnerId(0L));

    }

    /**
     * This method returns ALL reports for a specific owner by name and surname
     *
     * param firstName
     * param lastName
     * return List<Report>
     */
    @Test
    public void getAllReportsByOwnerName() {
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        Pet pet2 = new Pet("cat", LocalDate.of(2022, 5, 23));
        Pet pet3 = new Pet("snake", LocalDate.of(2022, 8, 5));
        LocalDate dateTime = LocalDate.of(2022, 5, 23);
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        Report report1 = new Report(dateTime, pet1, owner1, "Сухой корм",
                "Состояние здоровье: удовлетворительное",
                "Отсутствие поведенческих изменений", null, true);
        Report report2 = new Report(dateTime, pet2, owner1, "Сухой корм",
                "Состояние здоровье: Восхитительное",
                "Радуется как не в себя", null, true);
        Report report3 = new Report(dateTime, pet3, owner1, "Сухой корм",
                "Состояние здоровье: Плачевное",
                "Угнетенное состояние здоровья", null, true);
        List<Report> arrReports = new ArrayList<>();

        Mockito.when(reportRepository.findAllByOwnerFirstNameAndOwnerLastName("sheesh", "comrade")).thenReturn(arrReports);
        assertEquals(arrReports, reportService.getAllReportsByOwnerName("sheesh", "comrade"));
        assertNotNull(reportService.getAllReportsByOwnerName("sheesh", "comrade"));
    }

    /**
     * This method returns ALL unchecked reports for a specific owner by ID
     *
     * param ownerId
     * return List<Report>
     */
    @Test
    public void getAllUncheckedReportsByOwnerId() {
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        Pet pet2 = new Pet("cat", LocalDate.of(2022, 5, 23));
        Pet pet3 = new Pet("snake", LocalDate.of(2022, 8, 5));
        LocalDate dateTime = LocalDate.of(2022, 5, 23);
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        Report report1 = new Report(dateTime, pet1, owner1, "Сухой корм",
                "Состояние здоровье: удовлетворительное",
                "Отсутствие поведенческих изменений", null, true);
        Report report2 = new Report(dateTime, pet2, owner1, "Сухой корм",
                "Состояние здоровье: Восхитительное",
                "Радуется как не в себя", null, true);
        Report report3 = new Report(dateTime, pet3, owner1, "Сухой корм",
                "Состояние здоровье: Плачевное",
                "Угнетенное состояние здоровья", null, true);
        List<Report> arrReports = new ArrayList<>();

        Mockito.when(reportRepository.findAllByOwnerIdAndCorrectIsNull(0L)).thenReturn(arrReports);
        assertEquals(arrReports, reportService.getAllUncheckedReportsByOwnerId(0L));
        assertNotNull(reportService.getAllUncheckedReportsByOwnerId(0L));
    }

    /**
     * This method returns ALL unchecked reports for a specific owner by name and surname
     *
     * param firstName
     * param lastName
     * return List<Report>
     */
    @Test
    public void getAllUncheckedReportsByOwnerName() {
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        Pet pet2 = new Pet("cat", LocalDate.of(2022, 5, 23));
        Pet pet3 = new Pet("snake", LocalDate.of(2022, 8, 5));
        LocalDate dateTime = LocalDate.of(2022, 5, 23);
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        Report report1 = new Report(dateTime, pet1, owner1, "Сухой корм",
                "Состояние здоровье: удовлетворительное",
                "Отсутствие поведенческих изменений", null, null);
        Report report2 = new Report(dateTime, pet2, owner1, "Сухой корм",
                "Состояние здоровье: Восхитительное",
                "Радуется как не в себя", null, null);
        Report report3 = new Report(dateTime, pet3, owner1, "Сухой корм",
                "Состояние здоровье: Плачевное",
                "Угнетенное состояние здоровья", null, null);
        List<Report> arrReports = new ArrayList<>();

        Mockito.when(reportRepository.findAllByOwnerFirstNameAndOwnerLastNameAndCorrectIsNull("sheesh", "comrade")).thenReturn(arrReports);
        assertEquals(arrReports, reportService.getAllUncheckedReportsByOwnerName("sheesh", "comrade"));
        assertNotNull(reportService.getAllUncheckedReportsByOwnerName("sheesh", "comrade"));
    }

    /**
     * This method returns all reports from the database
     *
     * return List<Report>
     */
    @Test
    public void getAllUncheckedReports() {
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        Pet pet2 = new Pet("cat", LocalDate.of(2022, 5, 23));
        Pet pet3 = new Pet("snake", LocalDate.of(2022, 8, 5));
        LocalDate dateTime = LocalDate.of(2022, 5, 23);
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        Report report1 = new Report(dateTime, pet1, owner1, "Сухой корм",
                "Состояние здоровье: удовлетворительное",
                "Отсутствие поведенческих изменений", null, null);
        Report report2 = new Report(dateTime, pet2, owner1, "Сухой корм",
                "Состояние здоровье: Восхитительное",
                "Радуется как не в себя", null, null);
        Report report3 = new Report(dateTime, pet3, owner1, "Сухой корм",
                "Состояние здоровье: Плачевное",
                "Угнетенное состояние здоровья", null, true);
        List<Report> arrReports = new ArrayList<>();
        arrReports.add(report1);
        arrReports.add(report2);
        arrReports.add(report3);

        Mockito.when(reportRepository.findAllByIsCorrectIsNull()).thenReturn(arrReports);
        assertEquals(arrReports, reportService.getAllUncheckedReports());
        assertNotNull(reportService.getAllUncheckedReports());
    }

    /**
     * This method returns list of ids that did not send a daily report
     *
     * param int daysAgo
     * @return List<Long>
     */
    //TODO доделать
    @Test
    public void reportsChecker() {
        /*
        List<Long> ids = findAllIdsWithTrial();
        List<Long> warningIds = new ArrayList<>();
        LocalDate currentDate = LocalDate.now().minusDays(daysAgo);
        for (Long id : ids) {
            if (findReportByOwnerIdAndDate(id, currentDate)) {
                warningIds.add(id);
            }
        }
        return warningIds;

         */
    }

    /**
     * This method check yesterday report has arrived
     *
     * param Long ownerId
     * param LocalDate date
     * @return boolean
     */
    @Test
    private void findReportByOwnerIdAndDate() {
        /*
        Report report = reportRepository.findReportByOwnerIdAndIncomingReportTime_Date(ownerId, date);
        return report == null;

         */
    }

    /**
     * This method find all ids who have trial period
     *
     * @return List<Long>
     */
    @Test
    private void findAllIdsWithTrial() {
        List<Owner> arrOwners = new ArrayList<Owner>(3);
        List<Long> arrChatId = new ArrayList<>(3);
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        Pet pet2 = new Pet("cat", LocalDate.of(2022, 5, 23));
        Pet pet3 = new Pet("snake", LocalDate.of(2022, 8, 5));
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353", 20, volunteer1, pet1);
        Owner owner2 = new Owner(1L, "sheesh", "comrade", "+79245342353", 10, volunteer1, pet2);
        Owner owner3 = new Owner(2L, "sheesh", "comrade", "+79245342353", 30, volunteer1, pet3);
        arrOwners.add(owner1);
        arrOwners.add(owner2);
        arrOwners.add(owner3);
        arrChatId.add(0L);
        arrChatId.add(1L);
        arrChatId.add(2L);

        Mockito.when(ownerRepository.findAllByNumberOfReportDaysNotNull()).thenReturn(arrOwners);
        //assertEquals(arrChatId, reportService.findAllIdsWithTrial());
        //assertNotNull(reportService.findAllIdsWithTrial());

    }
}
