package com.skypro.java.petshelterbot.controller;

import com.skypro.java.petshelterbot.dto.ReportDto;
import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Volunteer;
import com.skypro.java.petshelterbot.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
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
     * @return ReportDto
     */
    @Operation(
            summary = "returns a report by idd",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "returns a report by id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter returns a report by id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })
    @GetMapping("/getReport/{reportId}")  // GET http:localhost:8080/report
    public ResponseEntity<ReportDto> getReport(@PathVariable Long reportId) {
        ReportDto report = reportService.getReport(reportId);
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
    @Operation(
            summary = "notifies the owner of the dog has passed the trial period",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "notifies the owner of the dog has passed the trial period",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter notifies the owner of the dog has passed the trial period",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })
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
    @Operation(
            summary = "notifies the owner of the dog has not passed the trial period",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "notifies the owner of the dog has not passed the trial period",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter  notifies the owner of the dog has not passed the trial period",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })
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
     * @return ReportDto
     */
    @Operation(
            summary = "marks the report as correctly completed",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "marks the report as correctly completed",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter  marks the report as correctly completed",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })
    @PutMapping("/markReportAsCorrect")
    public ResponseEntity<ReportDto> markReportAsCorrectById(@RequestParam(required = false) Long reportId) {
        ReportDto report = reportService.markReportAsCorrectById(reportId);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    /**
     * This method marks the report as incorrectly completed
     *
     * @param reportId
     * @return ReportDto
     */
    @Operation(
            summary = "marks the report as incorrectly completed",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "marks the report as incorrectly completed",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter  marks the report as incorrectly completed",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })
    @PutMapping("/markReportsAsIncorrect")
    public ResponseEntity<ReportDto> markReportsAsIncorrectById(@RequestParam(required = false) Long reportId) {
        ReportDto report = reportService.markReportsAsIncorrectById(reportId);
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
    @Operation(
            summary = "adds a specified number of days to the trial period for a specific owner",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "adds a specified number of days to the trial period for a specific owner",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter  adds a specified number of days to the trial period for a specific owner",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })
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
     * @param ownerId
     * @return List<ReportDto>
     */
    @Operation(
            summary = "returns ALL reports for a specific owner by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "returns ALL reports for a specific owner by ID",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter  ALL reports for a specific owner by ID",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })
    @GetMapping("/getAllReportsByOwnerId")
    public ResponseEntity<List<ReportDto>> getAllReportsByOwnerId(@RequestParam(required = false) Long ownerId) {
        List<ReportDto> reports = reportService.getAllReportsByOwnerId(ownerId);
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
     * @return List<ReportDto>
     */
    @Operation(
            summary = "returns ALL unchecked reports for a specific owner by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ALL reports for a specific owner by name and surname",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter name and surname ",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })
    @GetMapping("/getAllReportsByOwnerName")
    public ResponseEntity<List<ReportDto>> getAllReportsByOwnerName(@RequestParam(required = false) String firstName,
                                                                 @RequestParam(required = false) String lastName) {
        List<ReportDto> reports = reportService.getAllReportsByOwnerName(firstName, lastName);
        if (reports == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reports);
    }

    /**
     * This method returns ALL unchecked reports for a specific owner by ID
     *
     * @param ownerId
     * @return List<ReportDto>
     */
    @Operation(
            summary = "returns ALL unchecked reports for a specific owner by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "returns ALL unchecked reports for a specific owner by ID",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter all unchecked reports",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })
    @GetMapping("/getAllUncheckedReportsByOwnerId")
    public ResponseEntity<List<ReportDto>> getAllUncheckedReportsByOwnerId(@RequestParam(required = false) Long ownerId) {
        List<ReportDto> reports = reportService.getAllUncheckedReportsByOwnerId(ownerId);
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
     * @return List<ReportDto>
     */
    @Operation(
            summary = "returns ALL unchecked reports for a specific owner by name and surname",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "returns ALL unchecked reports for a specific owner by name and surname",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter all unchecked reports",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })
    @GetMapping("/getAllUncheckedReportsByOwnerName")
    public ResponseEntity<List<ReportDto>> getAllUncheckedReportsByOwnerName(@RequestParam(required = false) String firstName,
                                                                          @RequestParam(required = false) String lastName) {
        List<ReportDto> reports = reportService.getAllUncheckedReportsByOwnerName(firstName, lastName);
        if (reports == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reports);
    }

    /**
     * This method returns all reports from the database
     */
    @Operation(
            summary = "returns all reports",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "returns all reports",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter all reports",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })
    @GetMapping("/getAllUncheckedReports")
    public ResponseEntity<List<ReportDto>> getAllUncheckedReports() {
        List<ReportDto> reports = reportService.getAllUncheckedReports();
        return ResponseEntity.ok(reports);
    }

}
