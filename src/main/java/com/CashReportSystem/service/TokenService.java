package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.repository.UserRepository;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public TokenService() {
    }

    @Autowired
    private TokenRepository tokenRepo;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenHelper tokenhelper;

    public boolean validateToken(String tokenJsonObject) {
        JSONObject tokenJson = new JSONObject(tokenJsonObject);
        String token = tokenJson.getString("token");
        return tokenRepo.findByToken(token).isPresent();
    }

    public String findUserPermission(String tokenJsonObject) throws NoSuchUserException {
        JSONObject tokenJson = new JSONObject(tokenJsonObject);

        String token = tokenJson.getString("token");
        String username = tokenhelper.tokenParser(token);
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new NoSuchUserException("Username in token does not exist!"));

        String permission = user.getPermission();
        JSONObject responseObject = new JSONObject();
        responseObject.put("permission", permission);
        responseObject.put("username", username);

        return responseObject.toString();
    }
}
