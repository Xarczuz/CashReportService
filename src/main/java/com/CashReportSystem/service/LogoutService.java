package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoSuchToken;
import com.CashReportSystem.model.Token;
import com.CashReportSystem.repository.TokenRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    @Autowired
    TokenRepository tokenRepository;

    public void logoutUser(String jsonObject) throws NoSuchToken {
        JSONObject token = new JSONObject(jsonObject);

        Token existingToken = tokenRepository.findByToken(token.getString("token"))
                .orElseThrow(() -> new NoSuchToken("No Token."));

        tokenRepository.delete(existingToken);
    }

}
