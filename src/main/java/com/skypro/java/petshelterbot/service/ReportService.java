package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Report;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import com.skypro.java.petshelterbot.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final OwnerRepository ownerRepository;

    public ReportService(ReportRepository reportRepository, OwnerRepository ownerRepository) {
        this.reportRepository = reportRepository;
        this.ownerRepository = ownerRepository;
    }

    public Report markReportAsCorrectOrIncorrectById(Long reportId, String answer) {
        Report report = reportRepository.getReferenceById(reportId);
        if (report == null) {
            return null;
        }
        if (answer.equals("Correct")) {
            report.setCorrect(true);
        } else {
            report.setCorrect(false);
        }
        return reportRepository.save(report);
    }

    //TODO перенести в ОвнерСервис? Или оставить здесь, т.к. это относится к логике обработки репортов?
    public Owner addNumberOfReportDaysByOwnerId(Long ownerId, Integer numberOfDays) {
        Owner owner = ownerRepository.getReferenceById(ownerId);
        owner.setNumberOfReportDays(owner.getNumberOfReportDays() + numberOfDays);
        return ownerRepository.save(owner);
    }

    public Report getReport(Long reportId) {
        return reportRepository.getReferenceById(reportId);
    }

    public List<Report> getAllReportsByOwnerId(Long reportId) {
        return null;
    }

    public List<Report> getAllReportsByOwnerName(String firstName, String lastName) {
        return null;
    }

    public List<Report> getAllUncheckedReportsByOwnerId(Long reportId) {
        return null;
    }

    public List<Report> getAllUncheckedReportsByOwnerName(String firstName, String lastName) {
        return null;
    }
}
