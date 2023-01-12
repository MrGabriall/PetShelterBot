package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    @Query(value = "select * from volunteer  ", nativeQuery = true)
    List<Volunteer> showVolunteer();

}
