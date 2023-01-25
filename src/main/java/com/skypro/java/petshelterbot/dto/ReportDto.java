package com.skypro.java.petshelterbot.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReportDto {

    private Long id;
    private LocalDate incomingReportDate;
    private String petName;
    private String ownerName;
    private String petDiet;
    private String healthAndCondition;
    private String behavioralChanges;
    private String photoLink;

    public ReportDto(Long id,
                     LocalDate incomingReportDate,
                     String petName,
                     String ownerName,
                     String petDiet,
                     String healthAndCondition,
                     String behavioralChanges,
                     String photoLink) {
        this.id = id;
        this.incomingReportDate = incomingReportDate;
        this.petName = petName;
        this.ownerName = ownerName;
        this.petDiet = petDiet;
        this.healthAndCondition = healthAndCondition;
        this.behavioralChanges = behavioralChanges;
        this.photoLink = photoLink;
    }

    public ReportDto() {
    }
}
