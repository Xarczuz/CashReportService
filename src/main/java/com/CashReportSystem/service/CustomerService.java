package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoReportException;
import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.model.CustomerProfile;
import com.CashReportSystem.repository.CustomerProfileRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerProfileRepository customerProfileRepository;
    @Autowired
    TokenService tokenService;

    public String getAllCustomer(String tokenObject) throws NoSuchTokenException {
        validateToken(tokenObject);
        JSONObject customerObject = new JSONObject();
        JSONArray customerList = new JSONArray();

        customerObject.put("customerlist", customerList);

        List<CustomerProfile> reports = customerProfileRepository.findAll();
        reports.forEach(report -> customerList.put(report.toString()));
        return customerObject.toString();

    }

    public String addCustomer(String jsonObject) throws NoSuchTokenException, NoSuchUserException, NoPermissionException {
        JSONObject tokenAndCustomer = new JSONObject(jsonObject);
        String token = tokenAndCustomer.getString("token");

        validateToken(jsonObject);
        checkPermission(token, "admin", "employee");

        JSONObject customer = tokenAndCustomer.getJSONObject("customer");
        CustomerProfile customerToBeSaved = new CustomerProfile();
        customerToBeSaved.setId(customer.getLong("id"));
        customerToBeSaved.setCompanyName(customer.getString("companyName"));
        customerToBeSaved.setAddress(customer.getString("adress"));
        customerToBeSaved.setEmail(customer.getString("Email"));
        customerToBeSaved.setFirstName(customer.getString("firstName"));
        customerToBeSaved.setLastName(customer.getString("lastName"));
        customerToBeSaved.setOrgNr(customer.getString("orgNr"));
        customerToBeSaved.setPhoneNr(customer.getString("phoneNr"));
        CustomerProfile savedCustomer = customerProfileRepository.save(customerToBeSaved);

        JSONObject responseObject = new JSONObject();
        responseObject.put("customerid", savedCustomer.getId());

        return responseObject.toString();
    }

    public String removeCustomerByID(String jsonObject) throws NoSuchTokenException, NoPermissionException, NoSuchUserException, NoReportException {
        JSONObject tokenAndCustomerId = new JSONObject(jsonObject);
        String token = tokenAndCustomerId.getString("token");
        long customerId = tokenAndCustomerId.getLong("customerid");

        validateToken(jsonObject);
        checkPermission(token, "admin");

        if (customerProfileRepository.existsById(customerId)) {
            customerProfileRepository.deleteById(customerId);
            JSONObject responseObject = new JSONObject();
            responseObject.put("customerid", customerId);
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

    public String updateByCustomerId(String jsonObject) throws NoSuchTokenException, NoReportException {
        validateToken(jsonObject);
        JSONObject tokenAndCustomer = new JSONObject(jsonObject);

        JSONObject customer = tokenAndCustomer.getJSONObject("customer");

        CustomerProfile customerToBeSaved = new CustomerProfile();
        customerToBeSaved.setId(customer.getLong("id"));
        customerToBeSaved.setCompanyName(customer.getString("companyName"));
        customerToBeSaved.setAddress(customer.getString("adress"));
        customerToBeSaved.setEmail(customer.getString("Email"));
        customerToBeSaved.setFirstName(customer.getString("firstName"));
        customerToBeSaved.setLastName(customer.getString("lastName"));
        customerToBeSaved.setOrgNr(customer.getString("orgNr"));
        customerToBeSaved.setPhoneNr(customer.getString("phoneNr"));

        if (customerProfileRepository.existsById(customerToBeSaved.getId())) {

            CustomerProfile customerDb = customerProfileRepository.save(customerToBeSaved);

            JSONObject responseObject = new JSONObject();
            responseObject.put("customerid", customerDb.getId());
            return responseObject.toString();
        } else {
            throw new NoReportException("No such report with that id!");
        }
    }

}
