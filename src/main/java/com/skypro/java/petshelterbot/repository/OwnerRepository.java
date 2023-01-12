package com.skypro.java.petshelterbot.repository;


import com.skypro.java.petshelterbot.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner getOwnerByUserId(Long id);


    @Query(value = "select first_name,last_name,phone_number from owner  ", nativeQuery = true)
    List allOwner();


}
