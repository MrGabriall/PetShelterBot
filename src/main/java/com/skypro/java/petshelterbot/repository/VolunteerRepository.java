package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * class repository volunteer
 *
 * @author KiriukhinD
 */
@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    /**
     * search all column volunteer
     *
     * @return all list  column volunteer
     */
//    @Query(value = "select * from volunteer  ", nativeQuery = true)
//    List<Volunteer> showVolunteer();

    Volunteer getVolunteerById(Long id);

    @Query(value = "select * from volunteer  ", nativeQuery = true)
    List<Volunteer> getAll();

}
