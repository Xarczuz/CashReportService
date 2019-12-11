package com.CashReportSystem.System;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeTest {
    JSONObject token;
    JSONObject responseObject;
    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    EmployeeProfileRepository employeeProfileRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    void get_Employees() throws Exception {
        String randomToken = tokenHelper.tokenBuilder("tarem");

        token = new JSONObject();
        token.put("token", randomToken);

        EmployeeProfile employeeProfile = new EmployeeProfile();
        employeeProfile.setId((long) 1);
        employeeProfile.setEmployeeNr("1");
        employeeProfile.setRole("employee");
        employeeProfile.setFirstName("One");
        employeeProfile.setLastName("OneOne");
        employeeProfile.setEmail("One@mail.se");
        employeeProfile.setPhoneNr("070111111");

        EmployeeProfile employeeProfileTwo = new EmployeeProfile();
        employeeProfileTwo.setId((long) 2);
        employeeProfileTwo.setEmployeeNr("2");
        employeeProfileTwo.setRole("employeeTwo");
        employeeProfileTwo.setFirstName("Two");
        employeeProfileTwo.setLastName("TwoTwo");
        employeeProfileTwo.setEmail("Two@mail.se");
        employeeProfileTwo.setPhoneNr("0702222222");

        responseObject = new JSONObject();
        JSONArray employeeJsonArray = new JSONArray();
        responseObject.put("employeelist", employeeJsonArray);
        employeeJsonArray.put(employeeProfile);
        employeeJsonArray.put(employeeProfileTwo);

        employeeProfileRepository.save(employeeProfile);
        employeeProfileRepository.save(employeeProfileTwo);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/employeelist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseObject.toString()));
    }

}