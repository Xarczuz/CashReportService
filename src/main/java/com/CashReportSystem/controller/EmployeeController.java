package com.CashReportSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @PostMapping("/employeelist")
    public ResponseEntity<String> getEmployeeList(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("/employeelist_add")
    public ResponseEntity<String> addEmployee(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("/employeelist_remove")
    public ResponseEntity<String> removeEmployee(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("/employeelist_update")
    public ResponseEntity<String> updateEmployee(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }
}