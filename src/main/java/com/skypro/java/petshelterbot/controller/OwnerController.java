package com.skypro.java.petshelterbot.controller;


import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;


    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;

    }

    @PostMapping("/creat")
    public Owner create(@RequestBody Owner owner) {
        return ownerService.create(owner);
    }

    @GetMapping("/read/{id}")
    public Owner read(@PathVariable long id) {
        return ownerService.read(id);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Owner> update(@RequestBody Owner owner) {
        Owner owner1 = ownerService.update(owner);
        if (owner1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(owner1);

    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        ownerService.delete(id);

    }

    @GetMapping("/all")
    List showList() {
        return ownerService.showList();
    }

}