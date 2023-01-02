package com.skypro.java.petshelterbot.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Volunteer {
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

    public Volunteer(Long userId, String lastName, String firstName) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Volunteer() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Volunteer volunteer)) return false;
        return Objects.equals(id, volunteer.id)
                && Objects.equals(userId, volunteer.userId)
                && Objects.equals(lastName, volunteer.lastName)
                && Objects.equals(firstName, volunteer.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, lastName, firstName);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", userId=" + userId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
