package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.entity.Volunteer;
import com.skypro.java.petshelterbot.repository.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * class Volunteer
 *
 * @author KiriukhinD
 */
@Service
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;
    Logger logger = LoggerFactory.getLogger(VolunteerService.class);

    /**
     * constructor new obj search volunteerRepository
     * @param volunteerRepository -  new volunteerRepository
     * @see Volunteer#Volunteer()
     */
    VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /**
     * method create volunteer in database
     * @param volunteer - volunteer
     * @return new obj volunterr
     */
    public Volunteer create(Volunteer volunteer) {
        logger.info("method [create]");
        return volunteerRepository.save(volunteer);
    }

    /**
     * method read volunteer from database
     * @param id - volunteer
     * @return obj volunteer
     */
    public Volunteer read(long id) {
        logger.info("method [read]");
        return volunteerRepository.findById(id).get();
    }

    /**
     * method update volunteer in database
     * @param volunteer - volunteer
     * @return obj new volunteer
     */
    public Volunteer update(Volunteer volunteer) {
        logger.info("method [update]");
        return volunteerRepository.save(volunteer);
    }

    /**
     * method delete volunteer from database
     * @param id - volunteer
     * @return void
     */
    public ResponseEntity<Volunteer> delete(long id) {
        logger.info("method [delete]");
        volunteerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * method of getting the field value from database
     * @return all list Volunteer
     */
    public List showVolunteer() {
        return volunteerRepository.showVolunteer();
    }


}


