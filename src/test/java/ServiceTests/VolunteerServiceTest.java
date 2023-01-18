package ServiceTests;

import com.skypro.java.petshelterbot.repository.PetRepository;
import com.skypro.java.petshelterbot.repository.VolunteerRepository;
import com.skypro.java.petshelterbot.service.PetService;
import com.skypro.java.petshelterbot.service.VolunteerService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VolunteerServiceTest {

    @Mock
    private VolunteerRepository VolunteerRepository;

    @InjectMocks
    private VolunteerService volunteerService;
}
