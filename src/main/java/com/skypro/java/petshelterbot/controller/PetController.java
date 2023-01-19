package com.skypro.java.petshelterbot.controller;

import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.service.PetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
/**
 * @author EosReign
 * Class Controller PetController
 * CRUD - Create, Read, Update, Delete
 */


@RestController
@RequestMapping(path = "/pet")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Operation(summary = "Create Object Pet",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Create Object Pet",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pet.class)
                            )
                    )
            })
    @PostMapping(path = "/createPet")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        Pet newPet = petService.create(pet);
        if (newPet == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newPet);
    }

    @Operation(summary = "Search Object Pet by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Search Object Pet by ID",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pet.class)
                            )
                    )
            })
    @GetMapping(path = "/getPet")
    public ResponseEntity<Pet> readPet(@Parameter(description = "Write Pet ID", example = "2")
                                       @RequestParam("id") Long id) {
        Pet newPet = petService.read(id);
        if (newPet == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newPet);
    }

    @Operation(summary = "Edit Object Pet",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Edit Object Pet",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pet.class)
                    )
            )
    )
    @PutMapping(path = "/updatePet")
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet) {
        Pet newPet = petService.update(pet);
        if (newPet == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newPet);
    }

    @Operation(summary = "Delete Object Pet by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete Object Pet by ID",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pet.class)
                            )
                    )
            })
    @DeleteMapping(path = "/deletePet")
    public ResponseEntity deletePet(@Parameter(description = "Write Pet ID for deleting", example = "2")
                                    @PathVariable Long id) {
        petService.delete(id);
        return ResponseEntity.ok().build();
    }
}
