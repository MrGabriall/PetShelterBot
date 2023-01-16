package com.skypro.java.petshelterbot.entity;

import com.skypro.java.petshelterbot.bot.BotState;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(30)", name = "state")
    private BotState state;

    public UserState(Long chatId, BotState state) {
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

    public BotState getState() {
        return state;
    }

    public void setState(BotState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserState userState = (UserState) o;
        return Objects.equals(id, userState.id) && Objects.equals(chatId, userState.chatId) && state == userState.state;
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
