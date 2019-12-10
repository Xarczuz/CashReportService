package com.CashReportSystem.service;

import com.CashReportSystem.repository.TokenRepository;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public TokenService() {
    }

    public boolean validateToken(String tokenJsonObject) {
        JSONObject tokenJson = new JSONObject(tokenJsonObject);
        String token = tokenJson.getString("token");
        return tokenRepo.findByToken(token).isPresent();
    }
}
