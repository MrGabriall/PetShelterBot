package com.skypro.java.petshelterbot.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
/**
 * Назвала сущность Owner потому что с User есть проблемы в postgre. Нельзя создать таблицу с таким именем.
 */
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "passport_number")
    private Integer passportNumber;
    @Column(name = "number_of_report_days")
    private Integer numberOfReportDays;
    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    public Owner(Long userId, String lastName, String firstName, Integer passportNumber,
                 Integer numberOfReportDays, Volunteer volunteer) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.passportNumber = passportNumber;
        this.numberOfReportDays = numberOfReportDays;
        this.volunteer = volunteer;
    }

    public Owner() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner user)) return false;
        return Objects.equals(id, user.id)
                && Objects.equals(userId, user.userId)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(passportNumber, user.passportNumber)
                && Objects.equals(numberOfReportDays, user.numberOfReportDays)
                && Objects.equals(volunteer, user.volunteer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, lastName, firstName, passportNumber, numberOfReportDays, volunteer);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", passportNumber=" + passportNumber +
                ", numberOfReportDays=" + numberOfReportDays +
                ", volunteer=" + volunteer +
                '}';
    }
}
