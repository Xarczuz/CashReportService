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
        String permission = getPermission(token);

        JSONObject responseObject = new JSONObject();
        responseObject.put("permission", permission);
        responseObject.put("username", username);

        return responseObject.toString();
    }

    public boolean checkPermission(String token, String... permissionToCheck) throws NoSuchUserException {
        String permission = getPermission(token);
        for (String p : permissionToCheck) { //userPermission=employee -> equals("admin", "employee")
            if (permission.equals(p)) {
                return true;
            }
        }
        return false;
    }

    private String getPermission(String token) throws NoSuchUserException {
        String username = tokenhelper.tokenParser(token);
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new NoSuchUserException("Username in token does not exist!"));
        return user.getPermission();
    }
}
