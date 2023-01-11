package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

}
