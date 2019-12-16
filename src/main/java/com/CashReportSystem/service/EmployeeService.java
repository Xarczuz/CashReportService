package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeProfileRepository employeeProfileRepository;

    @Autowired
    TokenService tokenService;

    public String getAllEmployees(String tokenObject) throws NoSuchTokenException {

        tokenService.validateToken(tokenObject);
        JSONObject employeeObject = new JSONObject();
        JSONArray employeeList = new JSONArray();

        employeeObject.put("employeeList", employeeList);

        List<EmployeeProfile> employeeProfiles = employeeProfileRepository.findAll();
        employeeProfiles.forEach(employee -> employeeList.put(employee.toJsonObject()));
        return employeeObject.toString();
    }
}
