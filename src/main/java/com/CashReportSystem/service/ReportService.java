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
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TokenService tokenService;

    public String getAllReports(String tokenObject) throws NoSuchTokenException {
        if (!tokenService.validateToken(tokenObject)) {
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

    public String addReport(String jsonObject) throws NoSuchTokenException, NoSuchUserException {

        JSONObject tokenAndReport = new JSONObject(jsonObject);
        String token = tokenAndReport.getString("token");
        if (!tokenService.validateToken(token)) {
            throw new NoSuchTokenException("Not A Valid Token!");
        }
        try {
            if (tokenService.checkPermission(token, new String[]{"admin", "employee"})) {
                throw new NoPermissionException("admin doesnt exit");
            }
        } catch (NoSuchUserException e) {
            throw new NoSuchUserException("No such User" + "eeee");
        } catch (NoPermissionException e) {
            throw new NoSuchUserException("No PermissionException e");
        }
        JSONObject report = tokenAndReport.getJSONObject("report");
        Report reportToBeSaved = new Report();
        reportToBeSaved.setTableName(report.getString("tablename"));
        Report savedReport = reportRepository.save(reportToBeSaved);

        JSONObject responseObject = new JSONObject();
        responseObject.put("reportId", savedReport.getId());

        return responseObject.toString();
    }
}
