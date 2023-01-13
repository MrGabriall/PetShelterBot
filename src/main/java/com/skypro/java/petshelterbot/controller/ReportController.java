package com.skypro.java.petshelterbot.controller;

import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Report;
import com.skypro.java.petshelterbot.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Class serving for volunteer interaction with reports.
 *
 * @author nadillustrator
 */
@RestController
@RequestMapping(path = "/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * This method returns a report by id
     *
     * @param reportId
     * @return Report
     */
    @GetMapping("/getReport/{id}")  // GET http:localhost:8080/report
    public ResponseEntity<Report> getReport(@PathVariable Long reportId) {
        Report report = reportService.getReport(reportId);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    /**
     * This method notifies the owner of the dog has passed the trial period.
     *
     * @param ownerId
     * @return Owner
     */
    @GetMapping("/approveOwner")
    public ResponseEntity<Owner> approveOwner(@RequestParam(required = false) Long ownerId) {
        Owner answer = reportService.approveOwner(ownerId);
        if (answer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * This method notifies the owner of the dog has not passed the trial period.
     *
     * @param ownerId
     * @return Owner
     */
    @GetMapping("/denyOwner")
    public ResponseEntity<Owner> denyOwner(@RequestParam(required = false) Long ownerId) {
        Owner answer = reportService.denyOwner(ownerId);
        if (answer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     * This method marks the report as correctly completed
     *
     * @param reportId
     * @return Report
     */
    @PutMapping("/markReportAsCorrect")
    public ResponseEntity<Report> markReportAsCorrectById(@RequestParam(required = false) Long reportId) {
        Report report = reportService.markReportAsCorrectById(reportId);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    /**
     * This method marks the report as incorrectly completed
     *
     * @param reportId
     * @return Report
     */
    @PutMapping("/markReportsAsIncorrect")
    public ResponseEntity<Report> markReportsAsIncorrectById(@RequestParam(required = false) Long reportId) {
        Report report = reportService.markReportsAsIncorrectById(reportId);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    /**
     * This method adds a specified number of days to the trial period for a specific owner.
     *
     * @param ownerId
     * @param numberOfDays
     * @return Owner
     */
    @PutMapping("/addNumberOfReportDays")
    public ResponseEntity<Owner> addNumberOfReportDaysByOwnerId(@RequestParam(required = false) Long ownerId,
                                                                @RequestParam(required = false) Integer numberOfDays) {
        Owner owner = reportService.addNumberOfReportDaysByOwnerId(ownerId, numberOfDays);
        if (owner == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(owner);
    }

    /**
     * This method returns ALL reports for a specific owner by ID
     *
     * @param reportId
     * @return List<Report>
     */
    @GetMapping("/getAllReportsByOwnerId")
    public ResponseEntity<List<Report>> getAllReportsByOwnerId(@RequestParam(required = false) Long reportId) {
        List<Report> reports = reportService.getAllReportsByOwnerId(reportId);
        if (reports == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reports);
    }

    /**
     * This method returns ALL reports for a specific owner by name and surname
     *
     * @param firstName
     * @param lastName
     * @return List<Report>
     */
    @GetMapping("/getAllReportsByOwnerName")
    public ResponseEntity<List<Report>> getAllReportsByOwnerName(@RequestParam(required = false) String firstName,
                                                                 @RequestParam(required = false) String lastName) {
        List<Report> reports = reportService.getAllReportsByOwnerName(firstName, lastName);
        if (reports == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reports);
    }

    /**
     * This method returns ALL unchecked reports for a specific owner by ID
     *
     * @param reportId
     * @return List<Report>
     */
    @GetMapping("/getAllUncheckedReportsByOwnerId")
    public ResponseEntity<List<Report>> getAllUncheckedReportsByOwnerId(@RequestParam(required = false) Long reportId) {
        List<Report> reports = reportService.getAllUncheckedReportsByOwnerId(reportId);
        if (reports == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reports);
    }

    /**
     * This method returns ALL unchecked reports for a specific owner by name and surname
     *
     * @param firstName
     * @param lastName
     * @return List<Report>
     */
    @GetMapping("/getAllUncheckedReportsByOwnerName")
    public ResponseEntity<List<Report>> getAllUncheckedReportsByOwnerName(@RequestParam(required = false) String firstName,
                                                                          @RequestParam(required = false) String lastName) {
        List<Report> reports = reportService.getAllUncheckedReportsByOwnerName(firstName, lastName);
        if (reports == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reports);
    }

    /**
     * This method returns all reports from the database
     */
    @GetMapping("/getAllUncheckedReports")
    public ResponseEntity<List<Report>> getAllUncheckedReports() {
        List<Report> reports = reportService.getAllUncheckedReports();
        return ResponseEntity.ok(reports);
    }

}
