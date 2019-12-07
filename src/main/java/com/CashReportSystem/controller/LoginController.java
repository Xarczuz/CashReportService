package com.CashReportSystem.controller;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.UserRepository;
import com.CashReportSystem.security.PasswordHash;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenHelper tokenHelper;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String jsonObject) {

        JSONObject userJson = new JSONObject(jsonObject);

        User user = userRepository.findByUserName(userJson.get("username").toString()).get();
        if(user.getPassword().equals(PasswordHash.hashPassword(userJson.getString("password"),user.getSalt()))) {
            JSONObject responseObject  = new JSONObject();
            responseObject.put("token",tokenHelper.tokenBuilder(user.getUsername()));
            responseObject.put("permission",user.getPermission());

            return ResponseEntity.status(HttpStatus.OK).body(responseObject.toString());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

}
