package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    Logger logger = LoggerFactory.getLogger(OwnerService.class);


    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


    public Owner create(Owner owner) {
        logger.info("method [create]");
        return ownerRepository.save(owner);
    }

    public Owner read(long id) {
        logger.info("method [read]");
        return ownerRepository.findById(id).get();
    }

    public Owner update(Owner owner) {
        logger.info("method [update]");
        return ownerRepository.save(owner);
    }

    public ResponseEntity<Owner> delete(long id) {
        logger.info("method [delete]");
        ownerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public List showList() {
        return ownerRepository.allOwner();
    }


}
