package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author EosReign
 * Class Service PetService.
 * CRUD - Create, Read, Update, Delete
 */
@Service
public class PetService {

    private PetRepository petRepository;

    private PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PetService.class);

    /**
     * Method of insertion Object Pet into DB{@link Pet@create}
     * @return Return Object Pet
     */
    public Pet create(Pet pet) {
        LOGGER.debug("Method create was invoked");
        return petRepository.save(pet);
    }

    /**
     * Method of obtaining Object Pet from DB by ID{@link Pet@read}
     * @return Return Object Pet
     */
    public Pet read(Long id) {
        LOGGER.debug("Method read was invoked");
        return petRepository.getPetById(id);
    }
    /**
     * Method of saving new Object Pet(<b>updating</b>) in DB{@link Pet@update}
     * @return Return Object Pet
     */
    public Pet update(Pet pet) {
        LOGGER.debug("Method update was invoked");
        return petRepository.save(pet);
    }
    /**
     * Method of deleting Object Pet in DB by ID(<b>updating</b>) in DB{@link Pet@delete}
     * @return Return Nothing
     */
    public void delete(Long id) {
        LOGGER.debug("Method delete was invoked");
        petRepository.deleteById(id);
    }

    public Collection<Pet> findByType(String type) {
        LOGGER.debug("Method delete was invoked");
        return petRepository.findAllByPetTypeIgnoreCase(type);
    }

}
