package com.skypro.java.petshelterbot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
/**
 * @author nadillustrator
 * A class describing the volunteers who work at the shelter. The database is filled in by a volunteer.
 */
public class Volunteer {
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

    public Volunteer(Long chatId, String lastName, String firstName) {
        this.chatId = chatId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Volunteer() {
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
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
                && Objects.equals(chatId, volunteer.chatId)
                && Objects.equals(lastName, volunteer.lastName)
                && Objects.equals(firstName, volunteer.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, lastName, firstName);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
