package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.bot.TelegramBot;
import com.skypro.java.petshelterbot.entity.Photo;
import com.skypro.java.petshelterbot.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.mock.web.MockMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class PhotoSaverService {

    private final TelegramBot telegramBot;
    private final PhotoRepository photoRepository;
    @Value("./photos")
    private String photoDir;

    public PhotoSaverService(TelegramBot telegramBot, PhotoRepository photoRepository) {
        this.telegramBot = telegramBot;
        this.photoRepository = photoRepository;
    }

    public String readPhotoFromTelegram(Long id) {
        try {
            Photo photo = photoRepository.findById(id).orElseThrow();
            GetFile getFile = new GetFile();
            getFile.setFileId(photo.getFileId());
            File file = telegramBot.execute(getFile);
            System.out.println(file.toString());
            String file2 = telegramBot.downloadFile(file).getAbsolutePath();

//            InputStream is = telegramBot.downloadFileAsStream(file);
//            Path path = Paths.get(photoDir).resolve("" + id + ".jpeg");
//            Files.write(path, is.readAllBytes());
//            is.close();

            return file2;


        } catch (TelegramApiException e) {}

//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

}
