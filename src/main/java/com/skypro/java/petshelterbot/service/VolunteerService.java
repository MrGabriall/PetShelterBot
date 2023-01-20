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
     * {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param volunteer - volunteer
     * @return new obj volunteer
     */
    public Volunteer create(Volunteer volunteer) {
        logger.info("method [create]");
        return volunteerRepository.save(volunteer);
    }

    /**
     * method read volunteer from database
     * {@link org.springframework.data.jpa.repository.JpaRepository#findById(Object)}
     * @param id - volunteer
     * @return obj volunteer
     */
    public Volunteer read(long id) {
        logger.info("method [read]");
        return volunteerRepository.getVolunteerById(id);
    }

    /**
     * method update volunteer in database
     * {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param volunteer - volunteer
     * @return obj new volunteer
     */
    public Volunteer update(Volunteer volunteer) {
        logger.info("method [update]");
        return volunteerRepository.save(volunteer);
    }

    /**
     * method delete volunteer from database
     * {@link org.springframework.data.jpa.repository.JpaRepository#deleteById(Object)}
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
     * {@link VolunteerRepository#showVolunteer()}
     * @return all list Volunteer
     */
    public List showVolunteer() {
        return volunteerRepository.showVolunteer();
    }


}


