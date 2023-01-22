package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.entity.Photo;
import com.skypro.java.petshelterbot.entity.Report;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import com.skypro.java.petshelterbot.repository.PetRepository;
import com.skypro.java.petshelterbot.repository.PhotoRepository;
import com.skypro.java.petshelterbot.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import static com.skypro.java.petshelterbot.message.BotOutMessages.EXAMPLE_CORRECT_REPORT_MESSAGE;
import static com.skypro.java.petshelterbot.message.BotOutMessages.INCORRECT_REPORT_MESSAGE;

/**
 * This class is responsible for receiving and processing the report from the owner
 *
 * @author nadillustrator
 */
@Service
public class OwnerReportService {

    private final MessageService messageService;
    private final PhotoRepository photoRepository;
    private final ReportRepository reportRepository;
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    public OwnerReportService(MessageService messageService,
                              PhotoRepository photoRepository,
                              ReportRepository reportRepository,
                              OwnerRepository ownerRepository,
                              PetRepository petRepository) {
        this.messageService = messageService;
        this.photoRepository = photoRepository;
        this.reportRepository = reportRepository;
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
    }

    // TODO: Что делаем с этим методом?
    //TODO нужно переписать логику метода, чтобы отчет приходил поэтапно
    public SendMessage saveReport(Update update) {
        SendMessage answer = null;
        String fileId = update.getMessage().getPhoto().stream().findFirst().orElseThrow().getFileId();
        Photo photo = new Photo(fileId);
        Collection<PhotoSize> photos = update.getMessage().getPhoto();
        System.out.println(photos.toString());

        String caption = update.getMessage().getCaption();
        String[] allStrings = caption.split("\n", 3);
        if (allStrings.length != 3) {
            return messageService.sendMessage(update.getMessage().getChatId(),
                    INCORRECT_REPORT_MESSAGE + EXAMPLE_CORRECT_REPORT_MESSAGE);
//            throw new ArrayIndexOutOfBoundsException("The report from the userId " +
//            update.getMessage().getChatId() + " does not match the form");
        }

        Owner owner = ownerRepository.getOwnerByChatId(update.getMessage().getChatId());
        Pet pet = petRepository.getPetById(owner.getPet().getId());
        photoRepository.save(photo);


        Report report = new Report();
        report.setIncomingReportDate(LocalDate.now());
        report.setPet(pet);
        report.setOwner(owner);
        report.setPetDiet(allStrings[0]);
        report.setHealthAndCondition(allStrings[1]);
        report.setBehavioralChanges(allStrings[2]);
        report.setPhoto(photoRepository.getPhotoById(photo.getId()));
        reportRepository.save(report);

        System.out.println(report);
        return answer;
    }
}
