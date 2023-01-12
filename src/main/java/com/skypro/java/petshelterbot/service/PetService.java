package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author EosReign
 * Class Service PetService.
 * CRUD - Create, Read, Update, Delete
 */
@Service
public class PetService {

    private PetRepository petRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PetService.class);

    public Pet createPet(Pet pet) {
        LOGGER.debug("Method createPet was invoked");
        return petRepository.save(pet);
    }

    public Pet getById(Long id) {
        LOGGER.debug("Method getPet was invoked");
        return petRepository.getPetById(id);
    }

    public Pet updatePet(Pet pet) {
        LOGGER.debug("Method updatePet was invoked");
        return petRepository.save(pet);
    }

    public void deletePet(Long id) {
        LOGGER.debug("Method deletePet was invoked");
        petRepository.deleteById(id);
    }

}
