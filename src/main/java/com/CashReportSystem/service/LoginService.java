package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.exception.WrongPasswordException;
import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.Token;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.repository.UserRepository;
import com.CashReportSystem.security.PasswordHash;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenHelper tokenHelper;

    public LoginService() {

    }

    public JSONObject authenticateUser(String jsonObject) throws NoSuchUserException, WrongPasswordException {
        JSONObject loginUserJson = new JSONObject(jsonObject);

        User userInDb = userRepository.findByUserName(loginUserJson.get("username")
                .toString()).orElseThrow(() -> new NoSuchUserException("No such user!"));

        if (validatePassword(loginUserJson, userInDb)) {
            return createResponseObject(userInDb);
        }
        throw new WrongPasswordException("Wrong password.");
    }

    private JSONObject createResponseObject(User userInDb) {
        JSONObject responseObject = new JSONObject();
        String token = tokenHelper.tokenBuilder(userInDb.getUsername());
        saveToken(token);
        responseObject.put("token", token);
        responseObject.put("username", userInDb.getUsername());
        responseObject.put("permission", userInDb.getPermission());

        return responseObject;
    }

    private void saveToken(String token) {
        Token tokenToBeSaved = new Token(token);
        if (!tokenRepository.findByToken(token).isPresent()) {
            tokenRepository.save(tokenToBeSaved);
        }
    }

    private boolean validatePassword(JSONObject loginUserJson, User userInDb) {
        return userInDb.getPassword()
                .equals(PasswordHash.hashPassword(loginUserJson.getString("password"), userInDb.getSalt()));
    }
}
