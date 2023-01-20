package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Pet;
import com.skypro.java.petshelterbot.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Report getReportById(Long id);
    List<Report> findAllByOwnerId(Long id);

    Report findReportByOwnerIdAndIncomingReportTime_Date(Long ownerId, LocalDate Date);
    List<Report> findAllByOwnerFirstNameAndOwnerLastName(String firstName, String lastName);
    @Query(value = "select * from report r where r.owner_id = ?1 and r.is_correct IS NULL", nativeQuery = true)
    List<Report> findAllByOwnerIdAndCorrectIsNull(Long id);
    @Query(value = "select * from report " +
            "where owner_id in(select id from owner o " +
            "where o.first_name = ?1 and o.last_name = ?2) and is_correct IS NULL;", nativeQuery = true)
    List<Report> findAllByOwnerFirstNameAndOwnerLastNameAndCorrectIsNull(String firstName, String lastName);
}
