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
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    final TokenRepository tokenRepository;
    final UserRepository userRepository;
    final TokenHelper tokenHelper;

    public LoginService(TokenRepository tokenRepository, UserRepository userRepository, TokenHelper tokenHelper) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.tokenHelper = tokenHelper;
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
        String token = tokenHelper.tokenCryptBuilder(userInDb.getUsername());
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

    public boolean validatePassword(String username, String password) {
        User userInDb;
        try {
            userInDb = userRepository.findByUserName(username).orElseThrow(() -> new NoSuchUserException("No such user!"));
        } catch (NoSuchUserException e) {
            return false;
        }
        return userInDb.getPassword().equals(PasswordHash.hashPassword(password, userInDb.getSalt()));
    }
}
