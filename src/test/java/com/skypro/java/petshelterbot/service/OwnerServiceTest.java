package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.entity.Volunteer;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author EosReign
 */

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerService ownerService;

    @Test
    public void create() {
        Volunteer volunteer1 = new Volunteer(0L, "Main", "Man");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353", 20, volunteer1, pet1);

        Mockito.when(ownerRepository.save(owner1)).thenReturn(owner1);
        assertEquals(ownerService.create(owner1), owner1);
        assertNotNull(ownerService.create(owner1));
    }

    @Test
    public void read() {
        Volunteer volunteer1 = new Volunteer(0L, "Main", "Man");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353", 20, volunteer1, pet1);

        Mockito.when(ownerRepository.getOwnerById(0L)).thenReturn(owner1);
        assertEquals(ownerService.read(0L), owner1);
        assertNotNull(ownerService.read(0L));
    }

    @Test
    public void update() {
        Volunteer volunteer1 = new Volunteer(0L, "Main", "Man");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353", 20, volunteer1, pet1);

        Mockito.when(ownerRepository.save(owner1)).thenReturn(owner1);
        assertEquals(ownerService.update(owner1), owner1);
        assertNotNull(ownerService.update(owner1));
    }
/*
    @Test
    public ResponseEntity<Owner> delete(long id) {
        ownerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

 */

    @Test
    public void readAll() {
        List<Owner> arr = new ArrayList<Owner>(3);
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Pet pet1 = new Pet("doggy", LocalDate.of(2023, 12, 31));
        Pet pet2 = new Pet("cat", LocalDate.of(2022, 5, 23));
        Pet pet3 = new Pet("snake", LocalDate.of(2022, 8, 5));
        Owner owner1 = new Owner(0L, "sheesh", "comrade", "+79245342353", 20, volunteer1, pet1);
        Owner owner2 = new Owner(1L, "sheesh", "comrade", "+79245342353", 20, volunteer1, pet2);
        Owner owner3 = new Owner(2L, "sheesh", "comrade", "+79245342353", 20, volunteer1, pet3);
        arr.add(owner1);
        arr.add(owner2);
        arr.add(owner3);

        Mockito.when(ownerRepository.findAll()).thenReturn(arr);
        assertEquals(ownerService.readAll(), arr);
        assertNotNull(ownerService.readAll());
    }
}
