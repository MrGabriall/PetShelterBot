package com.skypro.java.petshelterbot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
/**
 * @author nadillustrator
 * The class that describes a dog owner who is on probation. The database is filled in by a volunteer.
 */
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "number_of_report_days")
    private Integer numberOfReportDays;
    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Owner(Long chatId, String lastName, String firstName, String phoneNumber,
                 Integer numberOfReportDays, Volunteer volunteer, Pet pet) {
        this.chatId = chatId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.numberOfReportDays = numberOfReportDays;
        this.volunteer = volunteer;
        this.pet = pet;
    }

    public Owner() {
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long userId) {
        this.chatId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String passportNumber) {
        this.phoneNumber = passportNumber;
    }

    public Integer getNumberOfReportDays() {
        return numberOfReportDays;
    }

    public void setNumberOfReportDays(Integer numberOfReportDays) {
        this.numberOfReportDays = numberOfReportDays;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner owner)) return false;
        return Objects.equals(id, owner.id) &&
                Objects.equals(chatId, owner.chatId) &&
                Objects.equals(lastName, owner.lastName) &&
                Objects.equals(firstName, owner.firstName) &&
                Objects.equals(phoneNumber, owner.phoneNumber) &&
                Objects.equals(numberOfReportDays, owner.numberOfReportDays) &&
                Objects.equals(volunteer, owner.volunteer) &&
                Objects.equals(pet, owner.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, lastName, firstName, phoneNumber, numberOfReportDays, volunteer, pet);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", numberOfReportDays=" + numberOfReportDays +
                ", volunteer=" + volunteer +
                ", pet=" + pet +
                '}';
    }
}
