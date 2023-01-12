package com.skypro.java.petshelterbot.controller;

import com.skypro.java.petshelterbot.entity.Volunteer;
import com.skypro.java.petshelterbot.service.VolunteerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;

    VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping("/create")
    public Volunteer create(@RequestBody Volunteer volunteer) {
        return volunteerService.create(volunteer);
    }

    @GetMapping("/read/{id}")
    public Volunteer read(@PathVariable long id) {
        return volunteerService.read(id);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Volunteer> update(@RequestBody Volunteer volunteer) {
        Volunteer volunteer1 = volunteerService.update(volunteer);
        if (volunteer1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(volunteer1);

    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        volunteerService.delete(id);

    }

    @GetMapping("/showVolunteer")
    public List showVolunteer() {
        return volunteerService.showVolunteer();

    }


}
