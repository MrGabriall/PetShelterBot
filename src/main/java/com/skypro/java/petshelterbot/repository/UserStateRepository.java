package com.skypro.java.petshelterbot.repository;

import com.skypro.java.petshelterbot.entity.UserState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStateRepository extends JpaRepository<UserState, Long> {
    UserState findByChatId(Long chatId);
}
