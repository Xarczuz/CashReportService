package com.CashReportSystem.repository;

import com.CashReportSystem.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepository extends JpaRepository <Token,Long> {
    @Query(value = "SELECT * FROM active_tokens_table WHERE token = :token",
            nativeQuery = true)
    Optional<Token> findByToken(String token);
}
