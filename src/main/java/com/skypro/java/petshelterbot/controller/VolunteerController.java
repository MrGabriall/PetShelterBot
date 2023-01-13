package com.skypro.java.petshelterbot.controller;

import com.skypro.java.petshelterbot.entity.Volunteer;
import com.skypro.java.petshelterbot.service.VolunteerService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
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


    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "created volunteer",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer[].class)
                    )
            )
    })


    @PostMapping("/create")
    public Volunteer create(@RequestBody Volunteer volunteer) {
        return volunteerService.create(volunteer);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "read volunteer",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer[].class)
                    )
            )
    })
    @GetMapping("/read/{id}")
    public Volunteer read(@PathVariable long id) {
        return volunteerService.read(id);

    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "update volunteer",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer[].class)
                    )
            )
    })

    @PutMapping("/update/{id}")
    public ResponseEntity<Volunteer> update(@RequestBody Volunteer volunteer) {
        Volunteer volunteer1 = volunteerService.update(volunteer);
        if (volunteer1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(volunteer1);

    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "deleted volunteer",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer[].class)
                    )
            )
    })

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        volunteerService.delete(id);

    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "All volunteeer",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer[].class)
                    )
            )
    })

    @GetMapping("/showVolunteer")
    public List showVolunteer() {
        return volunteerService.showVolunteer();

    }


}
