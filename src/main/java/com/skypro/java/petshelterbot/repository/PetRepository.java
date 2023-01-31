package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet getPetById(Long id);

    Collection<Pet> findAllByPetTypeIgnoreCase(String type);

}
