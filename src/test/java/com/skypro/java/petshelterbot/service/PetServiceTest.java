package com.skypro.java.petshelterbot.service;


import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.repository.PetRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author EosReign
 * Class ServiceTest PetServiceTest.
 * CRUD - Create, Read, Update, Delete
 */

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    public void createTest() {
        LocalDate date = LocalDate.of(2023, 12, 6);
        Pet pet1 = new Pet("doggy", "cat", date);
        Mockito.when(petRepository.save(pet1)).thenReturn(pet1);
        assertThat(pet1).isEqualTo(petService.create(pet1));
        assertThat(petService.create(pet1)).isNotNull();
    }

    @Test
    public void readTest() {
        LocalDate date = LocalDate.of(2023, 12, 6);
        Pet pet1 = new Pet("doggy", "cat", date);
        Mockito.when(petRepository.getPetById(1L)).thenReturn(pet1);
        assertThat(pet1).isEqualTo(petService.read(1L));
        assertThat(petService.read(1L)).isNotNull();
    }

    @Test
    public void updateTest() {
        LocalDate date = LocalDate.of(2023, 12, 6);
        Pet pet1 = new Pet("doggy", "cat", date);
        Mockito.when(petRepository.save(pet1)).thenReturn(pet1);
        assertThat(pet1).isEqualTo(petService.update(pet1));
        assertThat(petService.update(pet1)).isNotNull();
    }

    @Test
    public void deleteTest() {
        petService.delete(1L);
        Mockito.verify(petRepository).deleteById(1L);
    }

    @Test
    public void findByTypeTest() {
        LocalDate date = LocalDate.of(2023, 12, 6);
        Pet pet1 = new Pet("Jesus", "cat", date);
        Pet pet2 = new Pet("Puss", "cat", date);
        Pet pet3 = new Pet("Lucifer", "cat", date);
        List<Pet> arrCat = new ArrayList<>();
        arrCat.add(pet1);
        arrCat.add(pet2);
        arrCat.add(pet3);

        Mockito.when(petRepository.findAllByPetTypeIgnoreCase("cat")).thenReturn(arrCat);
        assertThat(arrCat).isEqualTo(petService.findByType("cat"));
    }
}
