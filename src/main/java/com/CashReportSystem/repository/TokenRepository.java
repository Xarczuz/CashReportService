package com.CashReportSystem.repository;

import com.CashReportSystem.model.Token;
import com.CashReportSystem.service.TokenService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepository extends JpaRepository <TokenService,Long> {
    @Query(value = "SELECT * FROM active_tokens WHERE token = :token",
            nativeQuery = true)
    Optional<Token> findByToken(String token);
}
