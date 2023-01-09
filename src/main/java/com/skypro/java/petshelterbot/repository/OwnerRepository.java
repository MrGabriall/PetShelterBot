package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
Owner getOwnerByUserId(Long id);
}
