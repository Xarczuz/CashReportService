package com.CashReportSystem.controller;

import com.CashReportSystem.exception.NoEmployeeException;
import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import com.CashReportSystem.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;

@RestController
@RequestMapping(value = "employee")
public class EmployeeController {

    final EmployeeService employeeService;

    final EmployeeProfileRepository employeeProfileRepository;

    public EmployeeController(EmployeeService employeeService, EmployeeProfileRepository employeeProfileRepository) {
        this.employeeService = employeeService;
        this.employeeProfileRepository = employeeProfileRepository;
    }

    @PostMapping("employeelist")
    public ResponseEntity<String> getEmployeeList(@RequestBody String tokenObject) {
        try {
            String responseObject = employeeService.getAllEmployees(tokenObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("employee_add")
    public ResponseEntity<String> addEmployee(@RequestBody String tokenObject) {

        try {
            String responseObject = employeeService.addEmployee(tokenObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException | NoSuchUserException | NoPermissionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("employee_remove")
    public ResponseEntity<String> removeEmployee(@RequestBody String tokenObject) {
        try {
            String responseObject = employeeService.removeEmployeeByID(tokenObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException | NoPermissionException | NoSuchUserException | NoEmployeeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("employee_update")
    public ResponseEntity<String> updateEmployee(@RequestBody String tokenObject) {
        try {
            String responseObject = employeeService.updateByEmployeeId(tokenObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException | NoEmployeeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}