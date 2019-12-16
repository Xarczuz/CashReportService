package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoEmployeeException;
import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.model.CustomerProfile;
import com.CashReportSystem.repository.CustomerProfileRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.Iterator;
import java.util.List;

@Service

public class CustomerService {
    @Autowired
    CustomerProfileRepository customerProfileRepository;
    @Autowired
    TokenService tokenService;

    public String getAllCustomers(String tokenObject) throws NoSuchTokenException {

        tokenService.validateToken(tokenObject);
        JSONObject customerObject = new JSONObject();
        JSONArray customerList = new JSONArray();

        customerObject.put("customerlist", customerList);

        List<CustomerProfile> employeeProfiles = customerProfileRepository.findAll();
        employeeProfiles.forEach(customer -> customerList.put(customer.toJsonObject()));

        return customerObject.toString();
    }

    public String addCustomer(String jsonObject) throws NoSuchTokenException, NoSuchUserException, NoPermissionException {
        JSONObject tokenAndCustomer = new JSONObject(jsonObject);
        String token = tokenAndCustomer.getString("token");

        tokenService.validateToken(jsonObject);
        tokenService.checkPermission(token, "admin", "employee");

        JSONObject customerJSONObject = tokenAndCustomer.getJSONObject("customer");
        CustomerProfile customerToBeSaved = new CustomerProfile();

        updateCustomerWithData(customerJSONObject, customerToBeSaved);

        CustomerProfile savedCustomer = customerProfileRepository.save(customerToBeSaved);

        JSONObject responseObject = new JSONObject();
        responseObject.put("id", savedCustomer.getId());

        return responseObject.toString();
    }

    public String removeCustomerByID(String jsonObject) throws NoSuchTokenException, NoPermissionException, NoSuchUserException, NoEmployeeException {
        JSONObject tokenAndCustomerId = new JSONObject(jsonObject);
        String token = tokenAndCustomerId.getString("token");
        long customerId = tokenAndCustomerId.getLong("id");

        tokenService.validateToken(jsonObject);
        tokenService.checkPermission(token, "admin");

        if (customerProfileRepository.existsById(customerId)) {
            customerProfileRepository.deleteById(customerId);
            JSONObject responseObject = new JSONObject();
            responseObject.put("id", customerId);
            return responseObject.toString();
        } else {
            throw new NoEmployeeException("No customer with that ID!");
        }
    }

    public String updateByCustomerId(String jsonObject) throws NoSuchTokenException, NoEmployeeException {
        tokenService.validateToken(jsonObject);
        JSONObject tokenAndCustomer = new JSONObject(jsonObject);

        JSONObject customerJSONObject = tokenAndCustomer.getJSONObject("customer");
        CustomerProfile customerToBeSaved = new CustomerProfile();

        updateCustomerWithData(customerJSONObject, customerToBeSaved);

        if (customerProfileRepository.existsById(customerToBeSaved.getId())) {
            CustomerProfile customerDb = customerProfileRepository.save(customerToBeSaved);
            JSONObject responseObject = new JSONObject();
            responseObject.put("id", customerDb.getId());
            return responseObject.toString();
        } else {
            throw new NoEmployeeException("No customer with that id!");
        }
    }

    private void updateCustomerWithData(JSONObject customerJSONObject, CustomerProfile customerToBeSaved) {
        Iterator<String> iterator = customerJSONObject.keys();

        while (iterator.hasNext()) {
            String key = iterator.next();
            switch (key) {
                case "id":
                    customerToBeSaved.setId(customerJSONObject.getLong("id"));
                    break;
                case "companyname":
                    customerToBeSaved.setCompanyName(customerJSONObject.getString("companyname"));
                    break;
                case "firstname":
                    customerToBeSaved.setFirstName(customerJSONObject.getString("firstname"));
                    break;
                case "lastname":
                    customerToBeSaved.setLastName(customerJSONObject.getString("lastname"));
                    break;
                case "phonenr":
                    customerToBeSaved.setPhoneNr(customerJSONObject.getString("phonenr"));
                    break;
                case "email":
                    customerToBeSaved.setEmail(customerJSONObject.getString("email"));
                    break;
                case "orgnr":
                    customerToBeSaved.setOrgNr(customerJSONObject.getString("orgnr"));
                    break;

                case "adress":
                    customerToBeSaved.setAddress(customerJSONObject.getString("adress"));
                    break;

            }
        }
    }

}
