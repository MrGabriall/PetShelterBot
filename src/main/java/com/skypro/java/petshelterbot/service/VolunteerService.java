package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.entity.Volunteer;
import com.skypro.java.petshelterbot.repository.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;
    Logger logger = LoggerFactory.getLogger(VolunteerService.class);

    VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }


    public Volunteer create(Volunteer volunteer) {
        logger.info("method [create]");
        return volunteerRepository.save(volunteer);
    }

    public Volunteer read(long id) {
        logger.info("method [read]");
        return volunteerRepository.findById(id).get();
    }

    public Volunteer update(Volunteer volunteer) {
        logger.info("method [update]");
        return volunteerRepository.save(volunteer);
    }

    public ResponseEntity<Volunteer> delete(long id) {
        logger.info("method [delete]");
        volunteerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public List showVolunteer() {
        return volunteerRepository.showVolunteer();
    }


}


