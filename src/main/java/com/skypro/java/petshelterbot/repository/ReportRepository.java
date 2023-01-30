package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Report getReportById(Long id);

    List<Report> findAllByOwnerId(Long id);

    List<Report> findAllByIsCorrectIsNull();

    @Query(value = "select * from report r where r.owner_id = ?1 and r.incoming_report_date = ?2", nativeQuery = true)
    Report findReportByOwnerIdAndIncomingReportTime_Date(@Param("ownerId") Long ownerId, @Param("date") LocalDate date);

    List<Report> findAllByOwnerFirstNameAndOwnerLastName(String firstName, String lastName);

    @Query(value = "select * from report r where r.owner_id = ?1 and r.is_correct IS NULL", nativeQuery = true)
    List<Report> findAllByOwnerIdAndCorrectIsNull(Long id);

    @Query(value = "select * from report " +
            "where owner_id in(select id from owner o " +
            "where o.first_name = ?1 and o.last_name = ?2) and is_correct IS NULL;", nativeQuery = true)
    List<Report> findAllByOwnerFirstNameAndOwnerLastNameAndCorrectIsNull(String firstName, String lastName);
}
