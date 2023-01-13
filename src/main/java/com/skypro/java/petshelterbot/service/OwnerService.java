package com.skypro.java.petshelterbot.service;

import com.skypro.java.petshelterbot.entity.Owner;
import com.skypro.java.petshelterbot.repository.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Class Owner allows you to work with properties.
 * @author gardenwow
 */
@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    Logger logger = LoggerFactory.getLogger(OwnerService.class);


    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     * Create owner the repository method is used {@link JpaRepository#save(Object)}
     * @param owner by json
     * @return object owner
     * @author gardenwow
     */

    public Owner create(Owner owner) {
        logger.info("method [create]");
        return ownerRepository.save(owner);
    }
    /**
     * Create owner the repository method is used {@link JpaRepository#findById(Object)}
     * @param id
     * @return object owner
     * @author gardenwow
     */

    public Owner read(long id) {
        logger.info("method [read]");
        return ownerRepository.findById(id).get();
    }

    /**
     * Update owner the repository method is used {@link JpaRepository#save(Object)}
     * @param owner
     * @return object owner
     * @author gardenwow
     */
    public Owner update(Owner owner) {
        logger.info("method [update]");
        return ownerRepository.save(owner);
    }

    /**
     * Update owner the repository method is used {@link JpaRepository#delete(Object)}
     * @param id
     * @return object owner
     * @author gardenwow
     */
    public ResponseEntity<Owner> delete(long id) {
        logger.info("method [delete]");
        ownerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Update owner the repository method is used {@link JpaRepository#findAll()}
     * @return Collection Owner
     * @author gardenwow
     */
    public Collection<Owner> readAll() {
        logger.info("method [update]");
        return ownerRepository.findAll();
    }


}
