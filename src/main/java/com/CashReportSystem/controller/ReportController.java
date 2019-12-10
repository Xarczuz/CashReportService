package com.CashReportSystem.controller;

import com.CashReportSystem.model.Report;
import com.CashReportSystem.repository.ReportRepository;
import com.CashReportSystem.repository.TokenRepository;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Armen
@RestController
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TokenRepository tokenRepository;

    @PostMapping("/reportlist")
    public ResponseEntity<String> getReportList(@RequestBody String tokenObject) {
        JSONObject reportObject = new JSONObject();
        JSONArray reportList = new JSONArray();

        reportObject.put("reportlist",reportList);

        List<Report> reports = reportRepository.findAll();
        reports.forEach(report -> {
            reportList.put(report.toString());
        });

        return ResponseEntity.status(HttpStatus.OK).body(reportObject.toString());
    }

    @PostMapping("/report_add")
    public ResponseEntity<String> addReport(@RequestBody String jsonObject) {
        if (jsonObject != null) {
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