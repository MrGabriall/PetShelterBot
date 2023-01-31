package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.TelegramBot;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author nadillustrator & mrgabriall
 */
@Service
public class PhotoSaverService {

    private final TelegramBot telegramBot;

    public PhotoSaverService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * This method open stream from telegram
     * and return pair of byte array and media type photo
     *
     * @param fileId photo fileId for telegram
     * @return Pair<byte [ ], String> byte array photo and string media type
     */
    public Pair<byte[], String> readPhotoFromTelegram(String fileId) {
        try {
            GetFile getFile = new GetFile();
            getFile.setFileId(fileId);
            File fileOut = telegramBot.execute(getFile); // telegram api

            String mimeType = Optional.ofNullable(fileOut.getFilePath())
                    .map(fileName -> fileName.substring(fileOut.getFilePath().lastIndexOf('.')))
                    .map(str -> "image" + str.replace('.', '/'))
                    .orElse("");

            byte[] bytes;
            try (InputStream is = telegramBot.downloadFileAsStream(fileOut)) {
                bytes = is.readAllBytes();
            }
            return Pair.of(bytes, mimeType);


        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
