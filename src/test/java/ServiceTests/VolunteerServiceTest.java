package ServiceTests;

import com.skypro.java.petshelterbot.entity.Volunteer;
import com.skypro.java.petshelterbot.repository.VolunteerRepository;
import com.skypro.java.petshelterbot.service.VolunteerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class VolunteerServiceTest {

    @Mock
    private VolunteerRepository volunteerRepository;

    @InjectMocks
    private VolunteerService volunteerService;

    @Test
    public void createTest() {
        Volunteer volunteer1 = new Volunteer(1L, "Man", "MANN");
        Mockito.when(volunteerRepository.save(volunteer1)).thenReturn(volunteer1);
        assertEquals(volunteerService.create(volunteer1), volunteer1);
        assertNotNull(volunteerService.create(volunteer1));
    }

    @Test
    public void readTest() {
        Volunteer volunteer1 = new Volunteer(1L, "Man", "MANN");
        Mockito.when(volunteerRepository.getVolunteerById(1L)).thenReturn(volunteer1);
        assertEquals(volunteerService.read(1L), volunteer1);
        assertNotNull(volunteerService.read(1L));
    }

    @Test
    public void updateTest() {
        Volunteer volunteer1 = new Volunteer(1L, "Man", "MANN");
        Mockito.when(volunteerRepository.save(volunteer1)).thenReturn(volunteer1);
        assertEquals(volunteerService.update(volunteer1), volunteer1);
        assertNotNull(volunteerService.update(volunteer1));
    }
/*
    @Test
    public void delete(long id) {
        Volunteer volunteer1 = new Volunteer(1L, "Man", "MANN");
        Mockito.when(volunteerRepository.deleteById(1L)).thenReturn(HttpStatus(OK));
        assertEquals(volunteerService.delete(1L), HttpStatus(OK));

    }
 */
    @Test
    public void showVolunteer() {
        List<Volunteer> arr = new ArrayList<Volunteer> (4);
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Volunteer volunteer2 = new Volunteer(1L, "Woman", "Mercedes");
        Volunteer volunteer3 = new Volunteer(2L, "Jesus", "Christ");
        Volunteer volunteer4 = new Volunteer(3L, "God", "Supreme");
        arr.add(volunteer1);
        arr.add(volunteer2);
        arr.add(volunteer3);
        arr.add(volunteer4);
        Mockito.when(volunteerRepository.showVolunteer()).thenReturn(arr);
        assertEquals(volunteerService.showVolunteer(), arr);
        assertNotNull(volunteerService.showVolunteer());
    }
}
