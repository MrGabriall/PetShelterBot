package com.skypro.java.petshelterbot.bot.botapi.handler;

import com.skypro.java.petshelterbot.bot.BotState;
import com.skypro.java.petshelterbot.bot.botapi.InputMessageHandler;
import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.entity.Photo;
import com.skypro.java.petshelterbot.entity.Report;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import com.skypro.java.petshelterbot.repository.PetRepository;
import com.skypro.java.petshelterbot.repository.PhotoRepository;
import com.skypro.java.petshelterbot.repository.ReportRepository;
import com.skypro.java.petshelterbot.service.MessageService;
import com.skypro.java.petshelterbot.service.UserStateService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.skypro.java.petshelterbot.message.BotCommands.*;

/**
 * Handler for filling_report state
 *
 * @author evnag
 */
@Component
public class FillingReportHandler implements InputMessageHandler {

    private final UserStateService userStateService;
    private final ReportRepository reportRepository;
    private final MessageService messageService;
    private final OwnerRepository ownerRepository;
    private final PhotoRepository photoRepository;
    private final PetRepository petRepository;


    public FillingReportHandler(UserStateService userStateService,
                                ReportRepository reportRepository,
                                MessageService messageService,
                                OwnerRepository ownerRepository,
                                PhotoRepository photoRepository,
                                PetRepository petRepository) {
        this.userStateService = userStateService;
        this.reportRepository = reportRepository;
        this.messageService = messageService;
        this.ownerRepository = ownerRepository;
        this.photoRepository = photoRepository;
        this.petRepository = petRepository;
    }

    /**
     * Processes a message in the context of a specific state
     *
     * @param message from update
     * @return SendMessage
     */
    @Override
    public SendMessage handle(Message message) {
        if (userStateService.getUserState(message).getState().equals(BotState.FILLING_REPORT)) {
            userStateService.setBotState(message.getChatId(), BotState.SEND_DIET_STATE);
        }
        return processUsersMessage(message);
    }

    /**
     * Gets the handler name
     *
     * @return BotState
     */
    @Override
    public BotState getHandler() {
        return BotState.FILLING_REPORT;
    }

    /**
     * Processes the user's text message and fills in the report
     *
     * @param message from update
     * @return {@link SendMessage}
     */
    private SendMessage processUsersMessage(Message message) {
        long chatId = message.getChatId();
        String userAnswer = message.getText();
        SendMessage messageToUser = null;

        Owner owner = ownerRepository.getOwnerByChatId(chatId);
        Pet pet = petRepository.getPetById(owner.getPet().getId());
        BotState botState = userStateService.getUserState(message).getState();
        Report report = checkForReportExists(owner.getId(), LocalDate.now());

        if (botState.equals(BotState.SEND_DIET_STATE)) {
            messageToUser = messageService.sendMessage(chatId, "SEND_DIET");
            userStateService.setBotState(chatId, BotState.SEND_HEALTH_STATE);
        }
        if (botState.equals(BotState.SEND_HEALTH_STATE)) {
            report.setPetDiet(userAnswer);
            reportRepository.save(report);
            messageToUser = messageService.sendMessage(chatId, "SEND_HEALTH");
            userStateService.setBotState(chatId, BotState.SEND_BEHAVIOR_STATE);
        }
        if (botState.equals(BotState.SEND_BEHAVIOR_STATE)) {
            report.setHealthAndCondition(userAnswer);
            reportRepository.save(report);
            messageToUser = messageService.sendMessage(chatId, "SEND_BEHAVIOR");
            userStateService.setBotState(chatId, BotState.SEND_PHOTO_STATE);
        }
        if (botState.equals(BotState.SEND_PHOTO_STATE)) {
            report.setBehavioralChanges(userAnswer);
            reportRepository.save(report);
            messageToUser = messageService.sendMessage(chatId, "SEND_PHOTO");
            userStateService.setBotState(chatId, BotState.REPORT_FILLED_STATE);
        }
        if (botState.equals(BotState.REPORT_FILLED_STATE)) {
            report.setPhoto(handlePhotoForReport(message));
            report.setPet(pet);
            report.setOwner(owner);
            report.setIncomingReportDate(LocalDate.now());
            reportRepository.save(report);

            userStateService.setBotState(chatId, BotState.START_STATE);
            messageToUser = messageService.sendReplyMessage(
                    chatId,
                    "Отчет отправлен",
                    messageService.generateMenuKeyBoard(CALL_VOLUNTEER, TO_MAIN_MENU));
        }
        return messageToUser;
    }

    /**
     * Checked the existence of a report on a specific date by the owner's ID
     * *
     * * @param ownerId owner's ID
     * * @param date LocalDate - specific date
     * * @return
     *
     * @param ownerId report`s ID
     * @return If the report is found, returns report . If the report is not found, returns a new report and writes it to the database
     */
    private Report checkForReportExists(Long ownerId, LocalDate date) {
        Report report = reportRepository.findReportByOwnerIdAndIncomingReportTime_Date(ownerId, date);
        if (report == null) {
            Report newReport = new Report();
            newReport.setOwner(ownerRepository.getOwnerById(ownerId));
            newReport.setIncomingReportDate(LocalDate.now());
            reportRepository.save(newReport);

            return newReport;
        }
        return report;
    }

    /**
     * Processes the user's photo message and save entity to db
     *
     * @param message from update
     * @return {@link Photo}
     */
    private Photo handlePhotoForReport(Message message) {
        List<PhotoSize> photos = message.getPhoto();
        String fileId = Objects.requireNonNull(photos.stream()
                .max(Comparator.comparing(PhotoSize::getFileSize))
                .orElse(null)).getFileId();
        Photo photo = new Photo(fileId);
        photoRepository.save(photo);
        return photo;
    }
}
