package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.entity.Volunteer;
import com.skypro.java.petshelterbot.repository.VolunteerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author EosReign
 */
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
        assertThat(volunteer1).isEqualTo(volunteerService.create(volunteer1));
        assertThat(volunteerService.create(volunteer1)).isNotNull();
    }

    @Test
    public void readTest() {
        Volunteer volunteer1 = new Volunteer(1L, "Man", "MANN");
        Mockito.when(volunteerRepository.getVolunteerById(1L)).thenReturn(volunteer1);
        assertThat(volunteer1).isEqualTo(volunteerService.read(1L));
        assertThat(volunteerService.read(1L)).isNotNull();
    }

    @Test
    public void updateTest() {
        Volunteer volunteer1 = new Volunteer(1L, "Man", "MANN");
        Mockito.when(volunteerRepository.save(volunteer1)).thenReturn(volunteer1);
        assertThat(volunteer1).isEqualTo(volunteerService.update(volunteer1));
        assertThat(volunteerService.update(volunteer1)).isNotNull();
    }

    @Test
    public void deleteTest() {
        volunteerService.delete(1L);
        Mockito.verify(volunteerRepository).deleteById(1L);
    }

    @Test
    public void getAllTest() {
        List<Volunteer> arr = new ArrayList<Volunteer>(4);
        Volunteer volunteer1 = new Volunteer(0L, "Man", "MANN");
        Volunteer volunteer2 = new Volunteer(1L, "Woman", "Mercedes");
        Volunteer volunteer3 = new Volunteer(2L, "Jesus", "Christ");
        Volunteer volunteer4 = new Volunteer(3L, "God", "Supreme");
        arr.add(volunteer1);
        arr.add(volunteer2);
        arr.add(volunteer3);
        arr.add(volunteer4);
        Mockito.when(volunteerRepository.getAll()).thenReturn(arr);
        assertThat(arr).isEqualTo(volunteerService.getAll());
        assertThat(volunteerService.getAll()).isNotNull();
    }
}
