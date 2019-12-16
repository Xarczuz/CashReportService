package com.CashReportSystem.controller;

import com.CashReportSystem.exception.NoEmployeeException;
import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.repository.CustomerProfileRepository;
import com.CashReportSystem.service.CustomerService;
import com.CashReportSystem.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;

@RestController
@RequestMapping(value = "customer")
public class CustomerController {
    @Autowired
    CustomerProfileRepository customerProfileRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    CustomerService customerService;

    @PostMapping("customerlist")
    public ResponseEntity<String> getCustomerList(@RequestBody String jsonObject) {

        try {
            String responseObject = customerService.getAllCustomers(jsonObject);

            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("customer_add")
    public ResponseEntity<String> addCustomer(@RequestBody String jsonObject) {
        try {
            String responseObject = customerService.addCustomer(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException | NoPermissionException | NoSuchUserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("customer_remove")
    public ResponseEntity<String> removeCustomer(@RequestBody String jsonObject) {
        try {
            String responseObject = customerService.removeCustomerByID(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);

        } catch (NoSuchTokenException | NoPermissionException | NoSuchUserException | NoEmployeeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("customer_update")
    public ResponseEntity<String> updateCustomer(@RequestBody String jsonObject) {
        try {
            String responseObject = customerService.updateByCustomerId(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException | NoEmployeeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}