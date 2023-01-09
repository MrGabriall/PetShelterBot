package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet getPetById(Long id);
}
