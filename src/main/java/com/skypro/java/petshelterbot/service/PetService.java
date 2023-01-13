package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * Method of insertion Object Pet into DB{@link Pet@createPet}
     * @return Return Object Pet
     */
    public Pet createPet(Pet pet) {
        LOGGER.debug("Method createPet was invoked");
        return petRepository.save(pet);
    }

    /**
     * Method of obtaining Object Pet from DB by ID{@link Pet@getById}
     * @return Return Object Pet
     */
    public Pet getById(Long id) {
        LOGGER.debug("Method getPet was invoked");
        return petRepository.getPetById(id);
    }
    /**
     * Method of saving new Object Pet(<b>updating</b>) in DB{@link Pet@getById}
     * @return Return Object Pet
     */
    public Pet updatePet(Pet pet) {
        LOGGER.debug("Method updatePet was invoked");
        return petRepository.save(pet);
    }
    /**
     * Method of deleting Object Pet in DB by ID(<b>updating</b>) in DB{@link Pet@getById}
     * @return Return Nothing
     */
    public void deletePet(Long id) {
        LOGGER.debug("Method deletePet was invoked");
        petRepository.deleteById(id);
    }

}
