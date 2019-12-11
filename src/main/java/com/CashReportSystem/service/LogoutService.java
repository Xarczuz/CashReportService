package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.model.Token;
import com.CashReportSystem.repository.TokenRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    @Autowired
    TokenRepository tokenRepository;

    public void logoutUser(String jsonObject) throws NoSuchTokenException {
        JSONObject token = new JSONObject(jsonObject);

        Token existingToken = tokenRepository.findByToken(token.getString("token"))
                .orElseThrow(() -> new NoSuchTokenException("No Token."));

        tokenRepository.delete(existingToken);
    }

}
