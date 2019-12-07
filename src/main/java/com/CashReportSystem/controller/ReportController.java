package com.CashReportSystem.controller;

import com.CashReportSystem.repository.ReportRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Armen
@RestController
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @GetMapping("/reportlist")
    public ResponseEntity<String> getReportList(@RequestBody String jsonObject) {

        if(jsonObject != null){
            JSONObject reportObject = new JSONObject(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(reportObject.toString());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("/report_add")
    public ResponseEntity<String> addReport(@RequestBody String jsonObject) {
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