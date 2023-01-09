package com.skypro.java.petshelterbot.service;


import com.skypro.java.petshelterbot.bot.TelegramBot;
import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.entity.Photo;
import com.skypro.java.petshelterbot.entity.Report;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import com.skypro.java.petshelterbot.repository.PetRepository;
import com.skypro.java.petshelterbot.repository.PhotoRepository;
import com.skypro.java.petshelterbot.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;

import static com.skypro.java.petshelterbot.message.BotOutMessages.EXAMPLE_CORRECT_REPORT_MESSAGE;


@Service
public class ReportService {

    private final PhotoRepository photoRepository;
    private final ReportRepository reportRepository;
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;


    public ReportService(PhotoRepository photoRepository, ReportRepository reportRepository, OwnerRepository ownerRepository, PetRepository petRepository) {
        this.photoRepository = photoRepository;
        this.reportRepository = reportRepository;
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
    }

    public void saveReport(Update update) {
        String fileId = update.getMessage().getPhoto().stream().findFirst().get().getFileId();
        Photo photo = new Photo(fileId);

        String caption = update.getMessage().getCaption();
        String[] allStrings = caption.split("\n", 3);//todo обработать ArrayIndexOutOfBoundsException

        if (allStrings.length != 3) {
            throw new ArrayIndexOutOfBoundsException("The report from the userId " + update.getMessage().getChatId() + " does not match the form");
        }

//            sendMessageService.sendMessage(update.getMessage().getChatId(), INCORRECT_REPORT_MESSAGE);
//            sendMessageService.sendMessage(update.getMessage().getChatId(), EXAMPLE_CORRECT_REPORT_MESSAGE);


        Owner owner = ownerRepository.getOwnerByUserId(update.getMessage().getChatId());
        Pet pet = petRepository.getPetById(owner.getPet().getId());
        photoRepository.save(photo);


        Report report = new Report();
        report.setIncomingReportTime(LocalDateTime.now());
        report.setPet(pet);
        report.setOwner(owner);
        report.setPetDiet(allStrings[0]);
        report.setHealthAndCondition(allStrings[1]);
        report.setBehavioralChanges(allStrings[2]);
        report.setPhoto(photoRepository.getPhotoById(photo.getId()));
        reportRepository.save(report);

        System.out.println(report.toString());
    }
}
