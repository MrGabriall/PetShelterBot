package com.skypro.java.petshelterbot.service;


import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.repository.PetRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
/**
 * @author EosReign
 * Class ServiceTest PetServiceTest.
 * CRUD - Create, Read, Update, Delete
 * Comment: Method Delete (IDK why i check this, if i dont have a test Repository)
 */

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    public void createPositive() {
        LocalDate date = LocalDate.of(2023, 12, 6);
        Pet pet1 = new Pet("doggy", petType, date);
        Mockito.when(petRepository.save(pet1)).thenReturn(pet1);
        assertEquals(petService.create(pet1), pet1);
        assertNotNull(petService.create(pet1));
    }

    @Test
    public void readPositive() {
        LocalDate date = LocalDate.of(2023, 12, 6);
        Pet pet1 = new Pet("doggy", petType, date);
        Mockito.when(petRepository.getPetById(1L)).thenReturn(pet1);
        assertEquals(petService.read(1L), pet1);
        assertNotNull(petService.read(1L));
    }

    @Test
    public void updatePositive() {
        LocalDate date = LocalDate.of(2023, 12, 6);
        Pet pet1 = new Pet("doggy", petType, date);
        Mockito.when(petRepository.save(pet1)).thenReturn(pet1);
        assertEquals(petService.update(pet1), pet1);
        assertNotNull(petService.update(pet1));
    }
    /**
     * Method of deleting Object Pet in DB by ID(<b>updating</b>) in DB{@link Pet@delete}
     * @return Return Nothing
     */
    @Test
    public void deletePositive() {
        /*
        LocalDate date = LocalDate.of(2023, 12, 6);
        Pet pet1 = new Pet("doggy", date);
        assertDoesNotThrow(petService.delete(1L));

         */
    }
}
