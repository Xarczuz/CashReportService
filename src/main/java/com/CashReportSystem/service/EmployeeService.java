package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.model.Report;
import com.CashReportSystem.repository.ReportRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TokenService tokenService;

    public String getAllEmployees(String tokenObject) throws NoSuchTokenException, NoPermissionException, NoSuchUserException {
        tokenService.validateToken(tokenObject);
        tokenService.checkPermission(tokenObject,"admin");

        JSONObject reportObject = new JSONObject();
        JSONArray reportList = new JSONArray();

        reportObject.put("reportlist", reportList);

        List<Report> reports = reportRepository.findAll();
        reports.forEach(report -> reportList.put(report.toJsonObject()));
        return reportObject.toString();
    }
}
