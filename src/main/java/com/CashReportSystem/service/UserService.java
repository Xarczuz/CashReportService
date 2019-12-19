package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoReportException;
import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {

    final UserRepository userRepository;
    final TokenService tokenService;

    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public String getAllUsers(String tokenObject) throws NoSuchTokenException {

        tokenService.validateToken(tokenObject);
        JSONObject userObject = new JSONObject();
        JSONArray usersList = new JSONArray();

        userObject.put("reportlist", usersList);

        List<User> users = userRepository.findAll();
        users.forEach(user -> usersList.put(user.toJsonObject()));
        return userObject.toString();
    }

    public String createUser(String jsonObject) throws NoSuchTokenException, NoPermissionException, NoSuchUserException {
        JSONObject tokenAndUser = new JSONObject(jsonObject);
        String token = tokenAndUser.getString("token");

        tokenService.validateToken(jsonObject);
        tokenService.checkPermission(token, "admin");

        JSONObject user = tokenAndUser.getJSONObject("user");
        User userToBeSaved = new User();
        userToBeSaved.setUsername(user.getString("username"));
        userToBeSaved.setPassword(user.getString("password"));
        userToBeSaved.setPermission(user.getString("permission"));

        User savedUser = userRepository.save(userToBeSaved);

        JSONObject responseObject = new JSONObject();
        responseObject.put("userid", savedUser.getId());

        return responseObject.toString();

    }

    public String removeUserByID(String jsonObject) throws NoSuchTokenException, NoPermissionException, NoSuchUserException, NoReportException {

        JSONObject tokenAndUserId = new JSONObject(jsonObject);
        String token = tokenAndUserId.getString("token");
        long userId = tokenAndUserId.getLong("userid");

        tokenService.validateToken(jsonObject);
        tokenService.checkPermission(token, "admin");

        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            JSONObject responseObject = new JSONObject();
            responseObject.put("userid", userId);
            return responseObject.toString();
        } else {
            throw new NoReportException("No user with that ID!");
        }
    }

    public String updateByUserId(String jsonObject) throws NoSuchTokenException, NoReportException {

        tokenService.validateToken(jsonObject);
        JSONObject tokenAndUser = new JSONObject(jsonObject);

        JSONObject userJSONObject = tokenAndUser.getJSONObject("report");
        User userToBeSaved = new User();

        updateReportWithData(userJSONObject, userToBeSaved);

        if (userRepository.existsById(userToBeSaved.getId())) {
            User databaseUser = userRepository.save(userToBeSaved);
            JSONObject responseObject = new JSONObject();
            responseObject.put("userid", databaseUser.getId());
            return responseObject.toString();
        } else {
            throw new NoReportException("No such user with that id!");
        }
    }

    private void updateReportWithData(JSONObject userJSONObject, User userToBeSaved) {
        Iterator<String> iterator = userJSONObject.keys();

        while (iterator.hasNext()) {
            String key = iterator.next();
            switch (key) {
                case "id":
                    userToBeSaved.setId(userJSONObject.getLong("id"));
                    break;
                case "username":
                    userToBeSaved.setUsername(userJSONObject.getString("username"));
                    break;
                case "password":
                    userToBeSaved.setPassword(userJSONObject.getString("password"));
                    break;
                case "salt":
                    userToBeSaved.setSalt(userJSONObject.getString("salt"));
                    break;
                case "permission":
                    userToBeSaved.setPermission(userJSONObject.getString("permission"));
                    break;

            }
        }
    }
}
