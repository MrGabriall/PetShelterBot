package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
