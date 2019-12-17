package com.CashReportSystem.controller;

import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.exception.WrongPasswordException;
import com.CashReportSystem.service.LoginService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String jsonObject) {
        try {
            JSONObject responseLoginObject = loginService.authenticateUser(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseLoginObject.toString());
        } catch (NoSuchUserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong User or User doesn't exist.");
        } catch (WrongPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong password.");
        }
    }

}
