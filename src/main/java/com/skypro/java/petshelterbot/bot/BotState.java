package com.skypro.java.petshelterbot.bot;

import static com.skypro.java.petshelterbot.command.BotCommands.*;

/**
 * Possible bot states
 */

public enum BotState {
    START_STATE(START),
    SEND_PHOTO_STATE("Отправьте/прикрепите фото"),
    SEND_DIET_STATE("Отправьте диету"),
    SEND_HEALTH_STATE("Отправьте сотояние здоровья"),
    SEND_BEHAVIOR_STATE("Отправьте поведение"),
    REPORT_SENT_STATE("Отчет отправлен!");

    private final String state;

    BotState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return this.state;
    }
}
