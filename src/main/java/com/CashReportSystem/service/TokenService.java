package com.CashReportSystem.service;

import com.CashReportSystem.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private TokenRepository tokenRepo;

    public TokenService() {
    }

    public TokenService(TokenRepository tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    public boolean validateToken(String token) {
        return tokenRepo.findByToken(token).isPresent();
    }
}
