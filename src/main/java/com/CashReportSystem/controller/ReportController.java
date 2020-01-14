package com.CashReportSystem.controller;

import com.CashReportSystem.exception.NoReportException;
import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;

@RestController
@RequestMapping(value = "report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("reportlist")
    public ResponseEntity<String> getReportList(@RequestBody String tokenObject) {
        try {
            String responseObject = reportService.getAllReports(tokenObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("report_add")
    public ResponseEntity<String> addReport(@RequestBody String jsonObject) {
        try {
            String responseObject = reportService.addReport(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException | NoPermissionException | NoSuchUserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("report_remove")
    public ResponseEntity<String> removeReport(@RequestBody String jsonObject) {
        try {
            String responseObject = reportService.removeReportByID(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);

        } catch (NoSuchTokenException | NoPermissionException | NoReportException | NoSuchUserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("report_update")
    public ResponseEntity<String> updateReport(@RequestBody String jsonObject) {
        try {
            String responseObject = reportService.updateByReportId(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }catch (NoReportException | NoSuchTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}