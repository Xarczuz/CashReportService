package com.CashReportSystem.controller;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import com.CashReportSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeProfileRepository employeeProfileRepository;

    @PostMapping("employeelist")
    public ResponseEntity<String> getEmployeeList(@RequestBody String tokenObject) {
        try {
            String responseObject = employeeService.getAllEmployees(tokenObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
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