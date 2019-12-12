package com.CashReportSystem.controller;

import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.model.CustomerProfile;
import com.CashReportSystem.repository.CustomerProfileRepository;
import com.CashReportSystem.service.TokenService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;
import java.util.List;

@RestController
@RequestMapping(value = "customer")
public class CustomerController {
    @Autowired
    CustomerProfileRepository customerProfileRepository;
    @Autowired
    TokenService tokenService;

    @PostMapping("customerlist")
    public ResponseEntity<String> getCustomerList(@RequestBody String jsonObject) {

        JSONObject parser = new JSONObject(jsonObject);
        System.out.println(parser.getString("token"));
        String[] permission = {"admin"};
        try {
            if (tokenService.checkPermission(parser.get("token").toString(), permission)) {

                JSONObject customer = new JSONObject();

                JSONArray customerList = new JSONArray();

                customer.put("customerlist", customerList);

                List<CustomerProfile> customerprofile = customerProfileRepository.findAll();
                customerprofile.forEach(customerProfiles -> {
                    customerList.put(customerProfiles.toString());

                });

                return ResponseEntity.status(HttpStatus.OK).body(customer.toString());

            }
        } catch (NoSuchUserException | NoPermissionException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The permission is not right");

    }

    @PostMapping("customerlist_add")
    public ResponseEntity<String> addCustomer(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("customerlist_remove")
    public ResponseEntity<String> removeCustomer(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }

    @PostMapping("customerlist_update")
    public ResponseEntity<String> updateCustomer(@RequestBody String jsonObject) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("TODO");
    }
}