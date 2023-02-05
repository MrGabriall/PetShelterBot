package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.TelegramBot;
import com.skypro.java.petshelterbot.dto.ReportDto;
import com.skypro.java.petshelterbot.entity.*;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import com.skypro.java.petshelterbot.repository.PhotoRepository;
import com.skypro.java.petshelterbot.repository.ReportRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


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
    private PhotoRepository photoRepository;

    @Mock
    private TelegramBot telegramBot;

    @Mock
    private SendMessage sendMessage;

    @InjectMocks
    private ReportService reportService;

    @Test
    public void getReportPositiveTest() {
        Pet pet = new Pet("Jesus", "CAT", LocalDate.of(2023, 3, 29));
        Volunteer volunteer = new Volunteer(1L, "Fallen", "Lucifer");
        Owner owner = new Owner(1L, "Supreme", "God", "+79547567425",
                7, volunteer, pet);
        Photo photo = new Photo("assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        photo.setId(1L);
        Report reportBefore = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Possable", "None", photo, true);
        reportBefore.setId(1L);
        ReportDto reportAfter = new ReportDto(1L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Possable", "None",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");

        Mockito.when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));
        Mockito.when(reportRepository.getReportById(1L)).thenReturn(reportBefore);
        assertThat(reportAfter).isEqualTo(reportService.getReport(1L));
        assertThat(reportService.getReport(1L)).isNotNull();
    }

    @Test
    public void getReportNegativeTest() {
        Mockito.when(reportRepository.getReportById(1L)).thenReturn(null);
        assertThat(reportService.getReport(1L)).isNull();
    }

    @Test
    public void approveOwnerPositiveTest() {
        Volunteer volunteer1 = new Volunteer(1L, "Main", "Man");
        Pet pet1 = new Pet("doggy", "cat", LocalDate.of(2023, 12, 31));
        Owner owner1 = new Owner(1L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        owner1.setId(1L);

        Mockito.when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner1));
        assertThat(owner1).isEqualTo(reportService.approveOwner(1L));
        assertThat(reportService.approveOwner(1L)).isNotNull();
    }

    @Test
    public void approveOwnerNegativeTest() {
        Mockito.when(ownerRepository.findById(1L)).thenReturn(null);
        assertThat(reportService.approveOwner(1L)).isNull();
    }

    @Test
    public void denyOwnerPositiveTest() {
        Volunteer volunteer1 = new Volunteer(1L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", "cat", LocalDate.of(2023, 12, 31));
        Owner owner1 = new Owner(1L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        owner1.setId(1L);

        Mockito.when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner1));
        assertThat(owner1).isEqualTo(reportService.denyOwner(1L));
        assertThat(reportService.denyOwner(1L)).isNotNull();
    }

    @Test
    public void denyOwnerNegativeTest() {
        Mockito.when(ownerRepository.findById(1L)).thenReturn(null);
        assertThat(reportService.denyOwner(1L)).isNull();
    }

    @Test
    public void markReportAsCorrectByIdPositiveTest() {
        Pet pet = new Pet("Jesus", "CAT", LocalDate.of(2023, 3, 29));
        Volunteer volunteer = new Volunteer(1L, "Fallen", "Lucifer");
        Owner owner = new Owner(1L, "Supreme", "God", "+79547567425",
                7, volunteer, pet);
        Photo photo = new Photo("assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        photo.setId(1L);
        Report reportBefore = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Possable", "None", photo, true);
        reportBefore.setId(1L);
        ReportDto reportAfter = new ReportDto(1L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Possable", "None",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");

        Mockito.when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));
        Mockito.when(reportRepository.findById(1L)).thenReturn(Optional.of(reportBefore));
        Mockito.when(reportRepository.save(reportBefore)).thenReturn(reportBefore);
        assertThat(reportAfter).isEqualTo(reportService.markReportAsCorrectById(1L));
        assertThat(reportService.markReportAsCorrectById(1L)).isNotNull();
    }

    @Test
    public void markReportAsCorrectByIdNegativeTest() {
        Mockito.when(reportRepository.findById(1L)).thenReturn(null);
        assertThat(reportService.markReportAsCorrectById(1L)).isNull();
    }

    @Test
    public void markReportsAsIncorrectByIdPositiveTest() {
        Pet pet = new Pet("Jesus", "CAT", LocalDate.of(2023, 3, 29));
        Volunteer volunteer = new Volunteer(1L, "Fallen", "Lucifer");
        Owner owner = new Owner(1L, "Supreme", "God", "+79547567425",
                7, volunteer, pet);
        Photo photo = new Photo("assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        photo.setId(1L);
        Report reportBefore = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Possable", "None", photo, false);
        reportBefore.setId(1L);
        ReportDto reportAfter = new ReportDto(1L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Possable", "None",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");

        Mockito.when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));
        Mockito.when(reportRepository.findById(1L)).thenReturn(Optional.of(reportBefore));
        Mockito.when(reportRepository.save(reportBefore)).thenReturn(reportBefore);
        assertThat(reportAfter).isEqualTo(reportService.markReportsAsIncorrectById(1L));
        assertThat(reportService.markReportsAsIncorrectById(1L)).isNotNull();
    }

    @Test
    public void markReportsAsIncorrectByIdNegativeTest() {
        Mockito.when(reportRepository.findById(1L)).thenReturn(null);
        assertThat(reportService.markReportsAsIncorrectById(1L)).isNull();
    }

    @Test
    public void addNumberOfReportDaysByOwnerIdPositiveTest() {
        Volunteer volunteer1 = new Volunteer(1L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", "cat", LocalDate.of(2023, 12, 31));
        Owner owner1 = new Owner(1L, "sheesh", "comrade", "+79245342353",
                20, volunteer1, pet1);
        owner1.setId(1L);

        Owner owner1New = new Owner(1L, "sheesh", "comrade", "+79245342353",
                30, volunteer1, pet1);
        owner1New.setId(1L);

        Mockito.when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner1));
        Mockito.when(ownerRepository.save(owner1New)).thenReturn(owner1New);
        assertThat(owner1New).isEqualTo(reportService.addNumberOfReportDaysByOwnerId(1L, 30));
        assertThat(reportService.addNumberOfReportDaysByOwnerId(1L, 30)).isNotNull();
    }

    @Test
    public void addNumberOfReportDaysByOwnerIdNegativeTest() {
        Mockito.when(ownerRepository.findById(1L)).thenReturn(null);
        assertThat(reportService.addNumberOfReportDaysByOwnerId(1L, 30)).isNull();
    }

    @Test
    public void getAllReportsByOwnerIdPositiveTest() {
        Pet pet = new Pet("Jesus", "CAT", LocalDate.of(2023, 3, 29));
        Volunteer volunteer = new Volunteer(1L, "Fallen", "Lucifer");
        Owner owner = new Owner(1L, "Supreme", "God", "+79547567425",
                7, volunteer, pet);
        Photo photo = new Photo("assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        photo.setId(1L);

        Report reportBefore1 = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Possable", "None", photo, true);
        reportBefore1.setId(1L);
        Report reportBefore2 = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Bad", "Dead", photo, true);
        reportBefore2.setId(2L);
        List<Report> arrReportsBefore = new ArrayList<>(2);
        arrReportsBefore.add(reportBefore1);
        arrReportsBefore.add(reportBefore2);

        ReportDto reportAfter1 = new ReportDto(1L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Possable", "None",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        ReportDto reportAfter2 = new ReportDto(2L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Bad", "Dead",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        List<ReportDto> arrReportsAfter = new ArrayList<>(2);
        arrReportsAfter.add(reportAfter1);
        arrReportsAfter.add(reportAfter2);

        Mockito.when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));
        Mockito.when(reportRepository.findAllByOwnerId(1L)).thenReturn(arrReportsBefore);
        assertThat(arrReportsAfter).isEqualTo(reportService.getAllReportsByOwnerId(1L));
        assertThat(reportService.getAllReportsByOwnerId(1L)).isNotNull();
    }

    @Test
    public void getAllReportsByOwnerIdNegativeTest() {
        Mockito.when(reportRepository.findAllByOwnerId(1L)).thenReturn(null);
        assertThat(reportService.getAllReportsByOwnerId(1L)).isNull();
    }

    @Test
    public void getAllReportsByOwnerNamePositiveTest() {
        Pet pet = new Pet("Jesus", "CAT", LocalDate.of(2023, 3, 29));
        Volunteer volunteer = new Volunteer(1L, "Fallen", "Lucifer");
        Owner owner = new Owner(1L, "Supreme", "God", "+79547567425",
                7, volunteer, pet);
        Photo photo = new Photo("assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        photo.setId(1L);

        Report reportBefore1 = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Possable", "None", photo, true);
        reportBefore1.setId(1L);
        Report reportBefore2 = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Bad", "Dead", photo, true);
        reportBefore2.setId(2L);
        List<Report> arrReportsBefore = new ArrayList<>(2);
        arrReportsBefore.add(reportBefore1);
        arrReportsBefore.add(reportBefore2);

        ReportDto reportAfter1 = new ReportDto(1L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Possable", "None",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        ReportDto reportAfter2 = new ReportDto(2L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Bad", "Dead",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        List<ReportDto> arrReportsAfter = new ArrayList<>(2);
        arrReportsAfter.add(reportAfter1);
        arrReportsAfter.add(reportAfter2);

        Mockito.when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));
        Mockito.when(reportRepository.findAllByOwnerFirstNameAndOwnerLastName("sheesh", "comrade")).thenReturn(arrReportsBefore);
        assertThat(arrReportsAfter).isEqualTo(reportService.getAllReportsByOwnerName("sheesh", "comrade"));
        assertThat(reportService.getAllReportsByOwnerName("sheesh", "comrade")).isNotNull();
    }

    @Test
    public void getAllReportsByOwnerNameNegativeTest() {
        Mockito.when(reportRepository.findAllByOwnerFirstNameAndOwnerLastName("Sheesh", "Loud")).thenReturn(null);
        assertThat(reportService.getAllReportsByOwnerName("Sheesh", "Loud")).isNull();
    }

    @Test
    public void getAllUncheckedReportsByOwnerIdPositiveTest() {
        Pet pet = new Pet("Jesus", "CAT", LocalDate.of(2023, 3, 29));
        Volunteer volunteer = new Volunteer(1L, "Fallen", "Lucifer");
        Owner owner = new Owner(1L, "Supreme", "God", "+79547567425",
                7, volunteer, pet);
        Photo photo = new Photo("assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        photo.setId(1L);

        Report reportBefore1 = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Possable", "None", photo, null);
        reportBefore1.setId(1L);
        Report reportBefore2 = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Bad", "Dead", photo, null);
        reportBefore2.setId(2L);
        List<Report> arrReportsBefore = new ArrayList<>(2);
        arrReportsBefore.add(reportBefore1);
        arrReportsBefore.add(reportBefore2);

        ReportDto reportAfter1 = new ReportDto(1L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Possable", "None",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        ReportDto reportAfter2 = new ReportDto(2L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Bad", "Dead",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        List<ReportDto> arrReportsAfter = new ArrayList<>(2);
        arrReportsAfter.add(reportAfter1);
        arrReportsAfter.add(reportAfter2);

        Mockito.when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));
        Mockito.when(reportRepository.findAllByOwnerIdAndCorrectIsNull(1L)).thenReturn(arrReportsBefore);
        assertThat(arrReportsAfter).isEqualTo(reportService.getAllUncheckedReportsByOwnerId(1L));
        assertThat(reportService.getAllUncheckedReportsByOwnerId(1L)).isNotNull();
    }

    @Test
    public void getAllUncheckedReportsByOwnerIdNegativeTest() {
        Mockito.when(reportRepository.findAllByOwnerIdAndCorrectIsNull(1L)).thenReturn(null);
        assertThat(reportService.getAllUncheckedReportsByOwnerId(1L)).isNull();
    }

    @Test
    public void getAllUncheckedReportsByOwnerNamePositiveTest() {
        Pet pet = new Pet("Jesus", "CAT", LocalDate.of(2023, 3, 29));
        Volunteer volunteer = new Volunteer(1L, "Fallen", "Lucifer");
        Owner owner = new Owner(1L, "Supreme", "God", "+79547567425",
                7, volunteer, pet);
        Photo photo = new Photo("assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        photo.setId(1L);

        Report reportBefore1 = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Possable", "None", photo, null);
        reportBefore1.setId(1L);
        Report reportBefore2 = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Bad", "Dead", photo, null);
        reportBefore2.setId(2L);
        List<Report> arrReportsBefore = new ArrayList<>(2);
        arrReportsBefore.add(reportBefore1);
        arrReportsBefore.add(reportBefore2);

        ReportDto reportAfter1 = new ReportDto(1L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Possable", "None",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        ReportDto reportAfter2 = new ReportDto(2L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Bad", "Dead",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        List<ReportDto> arrReportsAfter = new ArrayList<>(2);
        arrReportsAfter.add(reportAfter1);
        arrReportsAfter.add(reportAfter2);

        Mockito.when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));
        Mockito.when(reportRepository.findAllByOwnerFirstNameAndOwnerLastNameAndCorrectIsNull("sheesh", "comrade"))
                    .thenReturn(arrReportsBefore);
        assertThat(arrReportsAfter).isEqualTo(reportService.getAllUncheckedReportsByOwnerName("sheesh", "comrade"));
        assertThat(reportService.getAllUncheckedReportsByOwnerName("sheesh", "comrade")).isNotNull();
    }

    @Test
    public void getAllUncheckedReportsByOwnerNameNegativeTest() {
        Mockito.when(reportRepository.findAllByOwnerFirstNameAndOwnerLastNameAndCorrectIsNull("sheesh", "Supreme"))
                .thenReturn(null);
        assertThat(reportService.getAllUncheckedReportsByOwnerName("sheesh", "Supreme")).isNull();
    }

    @Test
    public void getAllUncheckedReports() {
        Pet pet = new Pet("Jesus", "CAT", LocalDate.of(2023, 3, 29));
        Volunteer volunteer = new Volunteer(1L, "Fallen", "Lucifer");
        Owner owner = new Owner(1L, "Supreme", "God", "+79547567425",
                7, volunteer, pet);
        Photo photo = new Photo("assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        photo.setId(1L);

        Report reportBefore1 = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Possable", "None", photo, null);
        reportBefore1.setId(1L);
        Report reportBefore2 = new Report(LocalDate.of(2023, 3, 29), pet, owner,
                "Vine", "Bad", "Dead", photo, null);
        reportBefore2.setId(2L);
        List<Report> arrReportsBefore = new ArrayList<>(2);
        arrReportsBefore.add(reportBefore1);
        arrReportsBefore.add(reportBefore2);

        ReportDto reportAfter1 = new ReportDto(1L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Possable", "None",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        ReportDto reportAfter2 = new ReportDto(2L, LocalDate.of(2023, 3, 29), "Jesus",
                "God Supreme", "Vine", "Bad", "Dead",
                "localhost:8080/report/photos/assets.churchofjesuschrist.org/b5/2d/b52d0825abd996169c1e0bbf97d6477489755290/jesus_christ.jpeg");
        List<ReportDto> arrReportsAfter = new ArrayList<>(2);
        arrReportsAfter.add(reportAfter1);
        arrReportsAfter.add(reportAfter2);

        Mockito.when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));
        Mockito.when(reportRepository.findAllByIsCorrectIsNull()).thenReturn(arrReportsBefore);
        assertThat(arrReportsAfter).isEqualTo(reportService.getAllUncheckedReports());
        assertThat(reportService.getAllUncheckedReports()).isNotNull();
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
    @Ignore
    @Test
    private void findAllIdsWithTrial() {
        List<Owner> arrOwners = new ArrayList<Owner>(3);
        List<Long> arrChatId = new ArrayList<>(3);
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", "cat", LocalDate.of(2023, 12, 31));
        Pet pet2 = new Pet("cat", "cat", LocalDate.of(2022, 5, 23));
        Pet pet3 = new Pet("snake", "cat", LocalDate.of(2022, 8, 5));
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
