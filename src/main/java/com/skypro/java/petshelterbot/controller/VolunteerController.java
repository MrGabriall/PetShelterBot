package com.skypro.java.petshelterbot.controller;

import com.skypro.java.petshelterbot.entity.Volunteer;
import com.skypro.java.petshelterbot.service.VolunteerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API class Volunteer controller
 *
 * @author KiriukhinD
 */
@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;

    /**
     * constructor new obj search volunteerRepository
     * @param volunteerService -  new volunteerservice
     * @see Volunteer#Volunteer()
     */
    VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    /**
     * @postmapping - controller
     * @return new obj volunteer
     */
    @Operation(
            summary = "create volunteer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "created volunteer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter volunteer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )


            })


    @PostMapping("/create")
    public Volunteer create(@RequestBody Volunteer volunteer) {
        return volunteerService.create(volunteer);
    }

    @Operation(
            summary = "update volunteer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "update volunteer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter volunteer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )
            })
    /**
     * @putmapping
     * @return - volunteer
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Volunteer> update(@RequestBody Volunteer volunteer) {
        Volunteer volunteer1 = volunteerService.update(volunteer);
        if (volunteer1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(volunteer1);

    }

    @Operation(
            summary = "delete volunteer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "deleted volunteer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter volunteer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )
            })

    @DeleteMapping("/delete/{id}")
    public void delete(@Parameter(description = "Enter {id} volunteer  for delete", example = "1") @PathVariable long id) {
        volunteerService.delete(id);

    }

    /**
     *
     * @return id - obj volunteer
     * @getmapping - controller
     */
    @Operation(
            summary = "read volunteer",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "search volunteer",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter volunteer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )
                    )

            })

    @GetMapping("/read/{id}")
    public Volunteer read(@Parameter(description = "Enter {id} volunteer for read ", example = "1") @PathVariable long id) {
        return volunteerService.read(id);

    }

    /**
     * @return list volunteer
     * @getmapping - controller
     */

    @Operation(
            summary = "show list volunteer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "all volunteer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Volunteer.class))

                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "wrong parameter volunteer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Volunteer.class))
                            )

                    )
            })
    @GetMapping("/showVolunteer")
    public List showVolunteer() {
        return volunteerService.showVolunteer();

    }


}
