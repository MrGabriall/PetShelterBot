package com.skypro.java.petshelterbot.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
/**
 * @author nadillustrator
 * A class that describes the daily report. The database is filled in by user.
 */
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "incoming_report_date")
    private LocalDate incomingReportDate;
    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @Column(name = "pet_diet")
    private String petDiet;
    @Column(name = "health_and_condition")
    private String healthAndCondition;
    @Column(name = "behavioral_changes")
    private String behavioralChanges;
    @OneToOne
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;
    @Column(name = "is_correct")
    private Boolean isCorrect;


    public Report(LocalDate incomingReportDate, Pet pet, Owner owner, String petDiet,
                  String healthAndCondition, String behavioralChanges, Photo photo, Boolean isCorrect) {
        this.incomingReportDate = incomingReportDate;
        this.pet = pet;
        this.owner = owner;
        this.petDiet = petDiet;
        this.healthAndCondition = healthAndCondition;
        this.behavioralChanges = behavioralChanges;
        this.photo = photo;
        this.isCorrect = isCorrect;
    }

    public Report() {
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner user) {
        this.owner = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getIncomingReportDate() {
        return incomingReportDate;
    }

    public void setIncomingReportDate(LocalDate incomingReportDate) {
        this.incomingReportDate = incomingReportDate;
    }

    public String getPetDiet() {
        return petDiet;
    }

    public void setPetDiet(String petDiet) {
        this.petDiet = petDiet;
    }

    public String getHealthAndCondition() {
        return healthAndCondition;
    }

    public void setHealthAndCondition(String healthAndCondition) {
        this.healthAndCondition = healthAndCondition;
    }

    public String getBehavioralChanges() {
        return behavioralChanges;
    }

    public void setBehavioralChanges(String behavioralChanges) {
        this.behavioralChanges = behavioralChanges;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report report)) return false;
        return Objects.equals(id, report.id) && Objects.equals(incomingReportDate, report.incomingReportDate) && Objects.equals(pet, report.pet) && Objects.equals(owner, report.owner) && Objects.equals(petDiet, report.petDiet) && Objects.equals(healthAndCondition, report.healthAndCondition) && Objects.equals(behavioralChanges, report.behavioralChanges) && Objects.equals(photo, report.photo) && Objects.equals(isCorrect, report.isCorrect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, incomingReportDate, pet, owner, petDiet, healthAndCondition, behavioralChanges, photo, isCorrect);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", incomingReportDate=" + incomingReportDate +
                ", pet=" + pet +
                ", owner=" + owner +
                ", petDiet='" + petDiet + '\'' +
                ", healthAndCondition='" + healthAndCondition + '\'' +
                ", behavioralChanges='" + behavioralChanges + '\'' +
                ", photo=" + photo +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
