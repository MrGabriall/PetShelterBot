package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.TelegramBot;
import com.skypro.java.petshelterbot.entity.Photo;
import com.skypro.java.petshelterbot.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.IOException;

@Service
public class PhotoSaverService {

    private final TelegramBot telegramBot;
    private final PhotoRepository photoRepository;

    public PhotoSaverService(TelegramBot telegramBot, PhotoRepository photoRepository) {
        this.telegramBot = telegramBot;
        this.photoRepository = photoRepository;
    }

    public java.io.File readPhotoFromTelegram(Long id) {
        try {
            Photo photo = photoRepository.findById(id).orElseThrow();
            GetFile getFile = new GetFile();
            getFile.setFileId(photo.getFileId());
            File file = telegramBot.execute(getFile);
            System.out.println(file.toString());
            java.io.File savedFile = telegramBot.downloadFile(file);
            System.out.println(savedFile.toString());
            return savedFile;


        } catch (TelegramApiException e) {

        }
        return null;
    }

}
