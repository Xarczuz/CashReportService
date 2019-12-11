package com.CashReportSystem.controller;

import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.repository.EmployeeProfileRepository;
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
@RequestMapping(value = "employee")
public class EmployeeController {

    @Autowired
    EmployeeProfileRepository employeeProfileRepository;

    @PostMapping("employeelist")
    public ResponseEntity<String> getEmployeeList(@RequestBody String jsonObject) {
        JSONObject employee = new JSONObject();

        JSONArray employeeList = new JSONArray();

        employee.put("employeelist",employeeList);

        List<EmployeeProfile> employeeProfile = employeeProfileRepository.findAll();
        employeeProfile.forEach(employeeProfile1 -> {
            employeeList.put(employeeProfile1.toString());
        });


        return ResponseEntity.status(HttpStatus.OK).body(employee.toString());
    }

    @PostMapping("employeelist_add")
    public ResponseEntity<String> addEmployee(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("employeelist_remove")
    public ResponseEntity<String> removeEmployee(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("employeelist_update")
    public ResponseEntity<String> updateEmployee(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }
}