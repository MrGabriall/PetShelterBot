package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner getOwnerByChatId(Long id);

    Owner getOwnerById(Long id);

    List<Owner> findAllByVolunteerId(Long volunteer_id);

    List<Owner> findAllByNumberOfReportDaysAfter(Integer numberOfReportDays);

    @Transactional
    @Modifying
    @Query(value = "update Owner set numberOfReportDays = numberOfReportDays - 1 where numberOfReportDays > 0")
    void updateNumberOfReportDays();
}
