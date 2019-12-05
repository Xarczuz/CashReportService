package com.CashReportSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @PostMapping("/customerlist")
    public ResponseEntity<String> getCustomerList(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("/customerlist_add")
    public ResponseEntity<String> addCustomer(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("/customerlist_remove")
    public ResponseEntity<String> removeCustomer(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("/customerlist_update")
    public ResponseEntity<String> updateCustomer(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }
}