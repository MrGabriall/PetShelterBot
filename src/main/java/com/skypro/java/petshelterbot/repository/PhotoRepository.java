package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
Photo getPhotoById(Long id);
}
