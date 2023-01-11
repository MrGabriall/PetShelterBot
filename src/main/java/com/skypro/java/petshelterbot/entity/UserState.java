package com.skypro.java.petshelterbot.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_state")
public class UserState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "chat_id")
    private Long chatId;
    private String state;

    public UserState(Long chatId, String state) {
        this.chatId = chatId;
        this.state = state;
    }

    public UserState() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserState userState)) return false;
        return Objects.equals(id, userState.id) && Objects.equals(chatId, userState.chatId) && Objects.equals(state, userState.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, state);
    }

    @Override
    public String toString() {
        return "UserState{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", state='" + state + '\'' +
                '}';
    }
}
