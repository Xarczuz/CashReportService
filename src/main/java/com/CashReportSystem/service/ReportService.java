package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoReportException;
import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.model.Report;
import com.CashReportSystem.repository.ReportRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.Iterator;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TokenService tokenService;

    public String getAllReports(String tokenObject) throws NoSuchTokenException{
        tokenService.validateToken(tokenObject);
        JSONObject reportObject = new JSONObject();
        JSONArray reportList = new JSONArray();

        reportObject.put("reportlist", reportList);

        List<Report> reports = reportRepository.findAll();
        reports.forEach(report -> reportList.put(report.toJsonObject()));
        return reportObject.toString();
    }

    public String addReport(String jsonObject) throws NoSuchTokenException, NoSuchUserException, NoPermissionException {
        JSONObject tokenAndReport = new JSONObject(jsonObject);
        String token = tokenAndReport.getString("token");

        tokenService.validateToken(jsonObject);
        tokenService.checkPermission(token, "admin", "employee");

        JSONObject report = tokenAndReport.getJSONObject("report");
        Report reportToBeSaved = new Report();
        reportToBeSaved.setGameTableName(report.getString("tablename"));
        Report savedReport = reportRepository.save(reportToBeSaved);

        JSONObject responseObject = new JSONObject();
        responseObject.put("reportid", savedReport.getId());

        return responseObject.toString();
    }

    public String removeReportByID(String jsonObject) throws NoSuchTokenException, NoPermissionException, NoSuchUserException, NoReportException {
        JSONObject tokenAndReportId = new JSONObject(jsonObject);
        String token = tokenAndReportId.getString("token");
        long reportId = tokenAndReportId.getLong("reportid");

        tokenService.validateToken(jsonObject);
        tokenService.checkPermission(token, "admin");

        if (reportRepository.existsById(reportId)) {
            reportRepository.deleteById(reportId);
            JSONObject responseObject = new JSONObject();
            responseObject.put("reportid", reportId);
            return responseObject.toString();
        } else {
            throw new NoReportException("No report with that ID!");
        }
    }

    public String updateByReportId(String jsonObject) throws NoSuchTokenException, NoReportException {
        tokenService.validateToken(jsonObject);
        JSONObject tokenAndReport = new JSONObject(jsonObject);

        JSONObject reportJSONObject = tokenAndReport.getJSONObject("report");
        Report reportToBeSaved = new Report();

        updateReportWithData(reportJSONObject, reportToBeSaved);

        if (reportRepository.existsById(reportToBeSaved.getId())) {
            Report databaseReport = reportRepository.save(reportToBeSaved);
            JSONObject responseObject = new JSONObject();
            responseObject.put("reportid", databaseReport.getId());
            return responseObject.toString();
        } else {
            throw new NoReportException("No such report with that id!");
        }
    }

    private void updateReportWithData(JSONObject reportJSONObject, Report reportToBeSaved) {
        Iterator<String> iterator = reportJSONObject.keys();

        while (iterator.hasNext()) {
            String key = iterator.next();
            switch (key) {
                case "id":
                    reportToBeSaved.setId(reportJSONObject.getLong("id"));
                    break;
                case "tablename":
                    reportToBeSaved.setGameTableName(reportJSONObject.getString("tablename"));
                    break;
                case "location":
                    reportToBeSaved.setLocation(reportJSONObject.getString("location"));
                    break;
                case "reportnr":
                    reportToBeSaved.setReportNr(reportJSONObject.getString("reportnr"));
                    break;
                case "employeesign":
                    reportToBeSaved.setEmployeeSign(reportJSONObject.getString("employeesign"));
                    break;
                case "customersign":
                    reportToBeSaved.setCustomerSign(reportJSONObject.getString("customersign"));
                    break;
                case "digitalcashflow":
                    reportToBeSaved.setDigitalCashFlow(reportJSONObject.getDouble("digitalcashflow"));
                    break;
                case "cashflow":
                    reportToBeSaved.setCashFlow(reportJSONObject.getDouble("cashflow"));
                    break;
                case "revenue":
                    reportToBeSaved.setRevenue(reportJSONObject.getDouble("revenue"));
                    break;
                case "payment":
                    reportToBeSaved.setPayment(reportJSONObject.getDouble("payment"));
                    break;
                case "infofield":
                    reportToBeSaved.setInfoField(reportJSONObject.getString("infofield"));
                    break;
                case "status":
                    reportToBeSaved.setStatus(reportJSONObject.getString("status"));
                    break;
            }
        }
    }

}
