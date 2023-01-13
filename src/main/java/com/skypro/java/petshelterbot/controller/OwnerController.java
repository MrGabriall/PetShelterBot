package com.skypro.java.petshelterbot.controller;


import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;


    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;

    }


    @Operation(summary = "create owner",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "create owner",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Owner.class)
                            )
                    )
            })
    @PostMapping("/creat")
    public Owner create(@RequestBody Owner owner) {
        return ownerService.create(owner);
    }

    @Operation(summary = "search for an owner by its Id in the table",
            responses = {

                    @ApiResponse(
                            responseCode = "200",
                            description = "search for an owner by its Id in the table ",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Owner.class))
                            )
                    )
            })
    @GetMapping("/read/{id}")
    public Owner read(@Parameter(description = "введи число от 1 до бесконечности", example = "2")
                      @PathVariable long id) {
        return ownerService.read(id);

    }

    @Operation(summary = "editing the owner",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "редактируемый овнер",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Owner.class)
                    )

            )
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<Owner> update(@RequestBody Owner owner) {
        Owner owner1 = ownerService.update(owner);
        if (owner1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(owner1);

    }

    @Operation(summary = "delete for an owner by its Id in the table",
            responses = {

                    @ApiResponse(
                            responseCode = "200",
                            description = "delete for an owner by its Id in the table ",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Owner.class))
                            )
                    )
            })

    @DeleteMapping("/delete/{id}")
    public void delete(@Parameter(description = "введи ид пользоветля которое хочешь удалить", example = "2")
                       @PathVariable long id) {
        ownerService.delete(id);

    }

    @Operation(summary = "search for all owner",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "search for all owners",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Owner[].class)
                            )
                    )
            })
    @GetMapping("/readall")
    public Collection<Owner> read() {
        return ownerService.readAll();
    }

}