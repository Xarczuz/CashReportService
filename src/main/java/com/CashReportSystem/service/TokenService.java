package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;

@Service
public class TokenService {

    private final TokenRepository tokenRepo;
    private final UserRepository userRepository;
    private final TokenHelper tokenhelper;

    public TokenService(TokenRepository tokenRepo, UserRepository userRepository, TokenHelper tokenhelper) {
        this.tokenRepo = tokenRepo;
        this.userRepository = userRepository;
        this.tokenhelper = tokenhelper;
    }

    public boolean validateToken(String tokenJsonObject) throws NoSuchTokenException {
        JSONObject tokenJson = new JSONObject(tokenJsonObject);
        String token = tokenJson.getString("token");

        if (!tokenRepo.findByToken(token).isPresent()) {
            throw new NoSuchTokenException("Token is not valid!");
        } else {
            return true;
        }
    }

    public boolean validateTokenString(String token) throws NoSuchTokenException {
        if (!tokenRepo.findByToken(token).isPresent()) {
            throw new NoSuchTokenException("Token is not valid!");
        } else {
            return true;
        }
    }

    public String findUserPermission(String tokenJsonObject) throws NoSuchUserException {
        JSONObject tokenJson = new JSONObject(tokenJsonObject);

        String token = tokenJson.getString("token");
        String username = tokenhelper.tokenParser(token);
        String permission = getPermission(token);

        JSONObject responseObject = new JSONObject();
        responseObject.put("permission", permission);
        responseObject.put("username", username);

        return responseObject.toString();
    }

    public boolean checkPermission(String token, String... permissionToCheck) throws NoSuchUserException, NoPermissionException {
        String permission = getPermission(token);
        for (String p : permissionToCheck) { //userPermission=employee -> equals("admin", "employee")
            if (permission.equals(p)) {
                return true;
            }
        }
        throw new NoPermissionException("No such Permission!");
    }

    private String getPermission(String token) throws NoSuchUserException {
        String username = tokenhelper.tokenParser(token);
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new NoSuchUserException("Username in token does not exist!"));
        return user.getPermission();
    }
}
