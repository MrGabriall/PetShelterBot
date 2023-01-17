package com.skypro.java.petshelterbot.bot;

/**
 * Possible bot states
 */

public enum BotState {
    START_STATE,
    OWNER_STATE,
    INFO_STATE,
    HOW_TO_ADOPT_STATE,

    SEND_CONTACTS_STATE,
    CALL_VOLUNTEER_STATE,

    FILLING_REPORT,
    SEND_PHOTO_STATE,
    SEND_DIET_STATE,
    SEND_HEALTH_STATE,
    SEND_BEHAVIOR_STATE,
    REPORT_FILLED_STATE
}
