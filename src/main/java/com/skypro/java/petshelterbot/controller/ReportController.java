package com.skypro.java.petshelterbot.controller;

import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Report;
import com.skypro.java.petshelterbot.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("{id}")  // GET http:localhost:8080/student/1
    public ResponseEntity<Report> getStudent(@PathVariable Long id) {
        Report report = reportService.getReport(id);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    @PutMapping("/5")
    public ResponseEntity<Report> markReportAsCorrectOrIncorrectById(@RequestParam(required = false) Long reportId, @RequestParam(required = false) String answer) {
        Report report = reportService.markReportAsCorrectOrIncorrectById(reportId, answer);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }


    @PutMapping("/6")
    public ResponseEntity<Owner> addNumberOfReportDaysByOwnerId(@RequestParam(required = false) Long ownerId, @RequestParam(required = false) Integer numberOfDays) {
        Owner owner = reportService.addNumberOfReportDaysByOwnerId(ownerId,numberOfDays);
        if (owner == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(owner);
    }

    @GetMapping("/1")
    public ResponseEntity<List<Report>> getAllReportsByOwnerId(@RequestParam(required = false) Long reportId) {
        List<Report> reports = reportService.getAllReportsByOwnerId(reportId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/2")
    public ResponseEntity<List<Report>> getAllReportsByOwnerName(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        List<Report> reports = reportService.getAllReportsByOwnerName(firstName, lastName);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/3")
    public ResponseEntity<List<Report>> getAllUncheckedReportsByOwnerId(@RequestParam(required = false) Long reportId) {
        List<Report> reports = reportService.getAllUncheckedReportsByOwnerId(reportId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/4")
    public ResponseEntity<List<Report>> getAllUncheckedReportsByOwnerName(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        List<Report> reports = reportService.getAllUncheckedReportsByOwnerName(firstName, lastName);
        return ResponseEntity.ok(reports);
    }


}
