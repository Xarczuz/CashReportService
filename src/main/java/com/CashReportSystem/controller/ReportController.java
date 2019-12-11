package com.CashReportSystem.controller;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.model.Report;
import com.CashReportSystem.repository.ReportRepository;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.service.ReportService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping("reportlist")
    public ResponseEntity<String> getReportList(@RequestBody String tokenObject) {
        try {
            String responseObject = reportService.getAllReports(tokenObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not A Valid Token!");
        }
    }

    @PostMapping("report_add")
    public ResponseEntity<String> addReport(@RequestBody String jsonObject) {
        try {
           String responseObject = reportService.addReport(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such token");
        }catch (NoSuchUserException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such user");
        }
    }

    @PostMapping("report_remove")
    public ResponseEntity<String> removeReport(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("report_update")
    public ResponseEntity<String> updateReport(@RequestBody String jsonObject) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }
}