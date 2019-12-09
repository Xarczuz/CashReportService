package com.CashReportSystem.controller;

import com.CashReportSystem.exception.NoSuchToken;
import com.CashReportSystem.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @Autowired
    LogoutService logoutService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String jsonObject) {
        try {
            logoutService.logoutUser(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body("Logout successful.");
        } catch (NoSuchToken noSuchToken) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such token.");
        }
    }
}
