package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoEmployeeException;
import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeProfileRepository employeeProfileRepository;
    private final TokenService tokenService;

    public EmployeeService(EmployeeProfileRepository employeeProfileRepository, TokenService tokenService) {
        this.employeeProfileRepository = employeeProfileRepository;
        this.tokenService = tokenService;
    }

    public String getAllEmployees(String tokenObject) throws NoSuchTokenException {

        tokenService.validateToken(tokenObject);
        JSONObject employeeObject = new JSONObject();
        JSONArray employeeList = new JSONArray();

        employeeObject.put("employeeList", employeeList);

        List<EmployeeProfile> employeeProfiles = employeeProfileRepository.findAll();
        employeeProfiles.forEach(employee -> employeeList.put(employee.toJsonObject()));
        return employeeObject.toString();
    }

    public String addEmployee(String jsonObject) throws NoSuchTokenException, NoSuchUserException, NoPermissionException {
        JSONObject tokenAndEmployee = new JSONObject(jsonObject);
        String token = tokenAndEmployee.getString("token");

        tokenService.validateToken(jsonObject);
        tokenService.checkPermission(token, "admin", "employee");

        JSONObject employeeJSONObject = tokenAndEmployee.getJSONObject("employee");
        EmployeeProfile employeeToBeSaved = new EmployeeProfile();

        updateEmployeeWithData(employeeJSONObject, employeeToBeSaved);

        EmployeeProfile savedEmployee = employeeProfileRepository.save(employeeToBeSaved);

        JSONObject responseObject = new JSONObject();
        responseObject.put("employeeid", savedEmployee.getId());

        return responseObject.toString();
    }

    public String removeEmployeeByID(String jsonObject) throws NoSuchTokenException, NoPermissionException, NoSuchUserException, NoEmployeeException {
        JSONObject tokenAndEmployeeId = new JSONObject(jsonObject);
        String token = tokenAndEmployeeId.getString("token");
        long employeeId = tokenAndEmployeeId.getLong("employeeid");

        tokenService.validateToken(jsonObject);
        tokenService.checkPermission(token, "admin");

        if (employeeProfileRepository.existsById(employeeId)) {
            employeeProfileRepository.deleteById(employeeId);
            JSONObject responseObject = new JSONObject();
            responseObject.put("employeeid", employeeId);
            return responseObject.toString();
        } else {
            throw new NoEmployeeException("No employee with that ID!");
        }
    }

    public String updateByEmployeeId(String jsonObject) throws NoSuchTokenException, NoEmployeeException {
        tokenService.validateToken(jsonObject);
        JSONObject tokenAndEmployee = new JSONObject(jsonObject);

        JSONObject employeeJSONObject = tokenAndEmployee.getJSONObject("employee");
        EmployeeProfile employeeToBeSaved = new EmployeeProfile();

        updateEmployeeWithData(employeeJSONObject, employeeToBeSaved);

        if (employeeProfileRepository.existsById(employeeToBeSaved.getId())) {
            EmployeeProfile databaseEmployee = employeeProfileRepository.save(employeeToBeSaved);
            JSONObject responseObject = new JSONObject();
            responseObject.put("employeeid", databaseEmployee.getId());
            return responseObject.toString();
        } else {
            throw new NoEmployeeException("No employee with that id!");
        }
    }

    private void updateEmployeeWithData(JSONObject employeeJSONObject, EmployeeProfile employeeToBeSaved) {
        Iterator<String> iterator = employeeJSONObject.keys();

        while (iterator.hasNext()) {
            String key = iterator.next();
            switch (key) {
                case "id":
                    employeeToBeSaved.setId(employeeJSONObject.getLong("id"));
                    break;
                case "employeenr":
                    employeeToBeSaved.setEmployeeNr(employeeJSONObject.getString("employeenr"));
                    break;
                case "role":
                    employeeToBeSaved.setRole(employeeJSONObject.getString("role"));
                    break;
                case "firstname":
                    employeeToBeSaved.setFirstName(employeeJSONObject.getString("firstname"));
                    break;
                case "lastname":
                    employeeToBeSaved.setLastName(employeeJSONObject.getString("lastname"));
                    break;
                case "phonenr":
                    employeeToBeSaved.setPhoneNr(employeeJSONObject.getString("phonenr"));
                    break;
                case "email":
                    employeeToBeSaved.setEmail(employeeJSONObject.getString("email"));
                    break;

            }
        }
    }

}
