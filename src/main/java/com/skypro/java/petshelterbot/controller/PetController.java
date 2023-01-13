package com.skypro.java.petshelterbot.controller;

import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.service.PetService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
/**
 * @author EosReign
 * Class Controller PetController
 * CRUD - Create, Read, Update, Delete
 */


@RestController
@RequestMapping(path = "/pet")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }
    /**
     * Method of creating Pet {@link Pet@createPet}
     * @return Return value of Pet
     */
    @PostMapping(path = "/createPet")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        Pet newPet = petService.createPet(pet);
        if (newPet == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newPet);
    }

    /**
     * Method of reading Pet {@link Pet@readPet}
     * @return Return value of Pet
     */
    @GetMapping(path = "/getPet")
    public ResponseEntity<Pet> readPet(@RequestParam("id") Long id) {
        Pet newPet = petService.getById(id);
        if (newPet == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newPet);
    }

    /**
     * Method of updating Pet {@link Pet@updatePet}
     * @return Return value of Pet
     */
    @PutMapping(path = "/updatePet")
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet) {
        Pet newPet = petService.updatePet(pet);
        if (newPet == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newPet);
    }

    /**
     * Method of deleting Pet {@link Pet@deletePet}
     * @return Return Nothing
     */
    @DeleteMapping(path = "/deletePet")
    public ResponseEntity deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.ok().build();
    }
}
