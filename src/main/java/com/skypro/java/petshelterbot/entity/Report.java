package com.skypro.java.petshelterbot.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "incoming_report_time")
    private LocalDateTime incomingReportTime;
    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @OneToOne
    @JoinColumn(name = "user_id")
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

    public Report(LocalDateTime incomingReportTime, Pet pet, Owner owner, String petDiet,
                  String healthAndCondition, String behavioralChanges, Photo photo) {
        this.incomingReportTime = incomingReportTime;
        this.pet = pet;
        this.owner = owner;
        this.petDiet = petDiet;
        this.healthAndCondition = healthAndCondition;
        this.behavioralChanges = behavioralChanges;
        this.photo = photo;
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

    public LocalDateTime getIncomingReportTime() {
        return incomingReportTime;
    }

    public void setIncomingReportTime(LocalDateTime incomingReportTime) {
        this.incomingReportTime = incomingReportTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(incomingReportTime, report.incomingReportTime) && Objects.equals(pet, report.pet) && Objects.equals(owner, report.owner) && Objects.equals(petDiet, report.petDiet) && Objects.equals(healthAndCondition, report.healthAndCondition) && Objects.equals(behavioralChanges, report.behavioralChanges) && Objects.equals(photo, report.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, incomingReportTime, pet, owner, petDiet, healthAndCondition, behavioralChanges, photo);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", incomingReportTime=" + incomingReportTime +
                ", pet=" + pet +
                ", user=" + owner +
                ", petDiet='" + petDiet + '\'' +
                ", healthAndCondition='" + healthAndCondition + '\'' +
                ", behavioralChanges='" + behavioralChanges + '\'' +
                ", photo=" + photo +
                '}';
    }
}
