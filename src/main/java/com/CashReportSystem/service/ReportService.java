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
import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TokenService tokenService;

    public String getAllReports(String tokenObject) throws NoSuchTokenException {
        validateToken(tokenObject);
        JSONObject reportObject = new JSONObject();
        JSONArray reportList = new JSONArray();

        reportObject.put("reportlist", reportList);

        List<Report> reports = reportRepository.findAll();
        reports.forEach(report -> reportList.put(report.toString()));
        return reportObject.toString();

    }

    public String addReport(String jsonObject) throws NoSuchTokenException, NoSuchUserException, NoPermissionException {
        JSONObject tokenAndReport = new JSONObject(jsonObject);
        String token = tokenAndReport.getString("token");

        validateToken(jsonObject);
        checkPermission(token, "admin", "employee");

        JSONObject report = tokenAndReport.getJSONObject("report");
        Report reportToBeSaved = new Report();
        reportToBeSaved.setTableName(report.getString("tablename"));
        Report savedReport = reportRepository.save(reportToBeSaved);

        JSONObject responseObject = new JSONObject();
        responseObject.put("reportid", savedReport.getId());

        return responseObject.toString();
    }

    public String removeReportByID(String jsonObject) throws NoSuchTokenException, NoPermissionException, NoSuchUserException, NoReportException {
        JSONObject tokenAndReportId = new JSONObject(jsonObject);
        String token = tokenAndReportId.getString("token");
        long reportId = tokenAndReportId.getLong("reportid");

        validateToken(jsonObject);
        checkPermission(token, "admin");

        if (reportRepository.existsById(reportId)) {
            reportRepository.deleteById(reportId);
            JSONObject responseObject = new JSONObject();
            responseObject.put("reportid", reportId);
            return responseObject.toString();
        } else {
            throw new NoReportException("No report with that ID!");
        }
    }

    private void checkPermission(String token, String... permission) throws NoSuchUserException, NoPermissionException {
        try {
            if (!tokenService.checkPermission(token, permission)) {
                throw new NoPermissionException("No PermissionException");
            }
        } catch (NoSuchUserException e) {
            throw new NoSuchUserException("No such User");
        } catch (NoPermissionException e) {
            throw new NoPermissionException("No PermissionException");
        }

    }

    private void validateToken(String jsonObject) throws NoSuchTokenException {
        if (!tokenService.validateToken(jsonObject)) {
            throw new NoSuchTokenException("Not A Valid Token!");
        }
    }

    public String updateByReportId(String jsonObject) throws NoSuchTokenException, NoReportException {
        validateToken(jsonObject);
        JSONObject tokenAndReport = new JSONObject(jsonObject);

        JSONObject reportJSONObject = tokenAndReport.getJSONObject("report");
        Report reportToBeSaved = new Report();
        reportToBeSaved.setId(reportJSONObject.getLong("id"));
        reportToBeSaved.setTableName(reportJSONObject.getString("tablename"));
        reportToBeSaved.setLocation(reportJSONObject.getString("location"));
        reportToBeSaved.setReportNr(reportJSONObject.getString("reportnr"));
        reportToBeSaved.setEmployeeList(reportJSONObject.getString("emplpyeelist"));
        reportToBeSaved.setEmployeeSign(reportJSONObject.getString("Employeesign"));
        reportToBeSaved.setCustomerSign(reportJSONObject.getString("customersign"));
        reportToBeSaved.setDigitalCashFlow(reportJSONObject.getDouble("digitalcashflow"));
        reportToBeSaved.setCashFlow(reportJSONObject.getDouble("cashflow"));
        reportToBeSaved.setRevenue(reportJSONObject.getDouble("revenue"));
        reportToBeSaved.setPayment(reportJSONObject.getDouble("payment"));
        reportToBeSaved.setPayment(reportJSONObject.getDouble("infofield"));
        reportToBeSaved.setStatus(reportJSONObject.getString("status"));

        if (reportRepository.existsById(reportToBeSaved.getId())){

            Report databaseReport = reportRepository.save(reportToBeSaved);

            JSONObject responseObject = new JSONObject();
            responseObject.put("repordid",databaseReport.getId());
            return responseObject.toString();
        }else{
            throw new NoReportException("No such report with that id!");
        }
    }

}
