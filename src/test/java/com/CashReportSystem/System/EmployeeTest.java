package com.CashReportSystem.System;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.model.Token;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import com.CashReportSystem.repository.TokenRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
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
    TokenRepository tokenRepository;

    @Autowired
    EmployeeProfileRepository employeeProfileRepository;
    @Autowired
    MockMvc mockMvc;

    static Boolean doOnce = true;

    @BeforeEach
    void setUp() {
        if (doOnce) {
            //Get
            String stringToken = tokenHelper.tokenBuilder("tarem");
            Token tokenToBeRepo = new Token(stringToken);
            tokenRepository.save(tokenToBeRepo);

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

            employeeProfileRepository.save(employeeProfile);
            employeeProfileRepository.save(employeeProfileTwo);


            //Add
            //Remove
            //Update

            doOnce = false;
        }
    }

    @Test
    void get_employees_list() throws Exception {
        String randomToken = tokenHelper.tokenBuilder("tarem");

        token = new JSONObject();
        token.put("token", randomToken);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/employeelist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void add_employee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/employeelist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseObject.toString()));
    }

    @Test
    void remove_employee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/employeelist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseObject.toString()));
    }

    @Test
    void update_employee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/employeelist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseObject.toString()));
    }
}