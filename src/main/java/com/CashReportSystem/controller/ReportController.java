package com.CashReportSystem.controller;

import com.CashReportSystem.repository.ReportRepository;
import com.CashReportSystem.repository.TokenRepository;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Armen
@RestController
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TokenRepository tokenRepository;

    @PostMapping("/reportlist")
    public ResponseEntity<String> getReportList(@RequestBody String token) {

        if(token != null){
            JSONObject reportObject = new JSONObject();

            return ResponseEntity.status(HttpStatus.OK).body(reportObject.toString());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Reports.");
    }

    @PostMapping("/report_add")
    public ResponseEntity<String> addReport(@RequestBody String jsonObject) {
        if(jsonObject != null){
            JSONObject reportAddObject = new JSONObject(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(reportAddObject.toString());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("/report_remove")
    public ResponseEntity<String> removeReport(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("/report_delete")
    public ResponseEntity<String> deleteReport(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }
}