package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.model.Report;
import com.CashReportSystem.repository.ReportRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TokenService tokenService;

    public String getAllReports(String tokenObject) throws NoSuchTokenException{
        if(!tokenService.validateToken(tokenObject)) {
            throw new NoSuchTokenException("Not A Valid Token!");
        }
            JSONObject reportObject = new JSONObject();
            JSONArray reportList = new JSONArray();

            reportObject.put("reportlist", reportList);

            List<Report> reports = reportRepository.findAll();
            reports.forEach(report -> {
                reportList.put(report.toString());
            });
            return reportObject.toString();

    }
}
