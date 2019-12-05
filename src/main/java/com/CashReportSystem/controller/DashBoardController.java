package com.CashReportSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashBoardController {

    @PostMapping("/dashboard")
    public ResponseEntity<String> dashboard(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("/profile_update")
    public ResponseEntity<String> updateProfile(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

}
