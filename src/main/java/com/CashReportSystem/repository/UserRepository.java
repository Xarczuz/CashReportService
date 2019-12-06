package com.CashReportSystem.repository;

import com.CashReportSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user_profile_table WHERE username = :user_name_to_find",
            nativeQuery = true)
    Optional<User> findByUserName(String user_name_to_find);
}
