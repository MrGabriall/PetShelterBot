package ServiceTests;


import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.repository.PetRepository;
import com.skypro.java.petshelterbot.service.PetService;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @BeforeEach

    @AfterEach

    @Test
    public void createPetPositive() {
        LocalDate date = LocalDate.of(2023, 12, 6); // sheesh, what a class...
        Pet pet1 = new Pet("doggy", date);
        Mockito.when(petRepository.save(pet1)).thenReturn(pet1);
        assertEquals(petService.createPet(pet1), pet1);
    }

    @Test
    public void getByIdPositive() {
        LocalDate date = LocalDate.of(2023, 12, 6); // sheesh, what a class...
        Pet pet1 = new Pet("doggy", date);
        Mockito.when(petRepository.getPetById(1L)).thenReturn(pet1);
        assertEquals(petService.getById(1L), pet1);
    }

    @Test
    public void updatePetPositive() {
        LocalDate date = LocalDate.of(2023, 12, 6); // sheesh, what a class...
        Pet pet1 = new Pet("doggy", date);

    }

    @Test
    public void deletePet() {

    }




}
