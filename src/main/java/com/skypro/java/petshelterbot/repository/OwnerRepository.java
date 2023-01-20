package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner getOwnerByChatId(Long id);
    Owner getOwnerById(Long id);

    List<Owner> findAllByNumberOfReportDaysNotNull();
}
