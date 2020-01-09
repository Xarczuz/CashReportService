package com.CashReportSystem.controller;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.EmployeeProfile;
import com.CashReportSystem.model.Token;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.EmployeeProfileRepository;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.repository.UserRepository;
import org.json.JSONException;
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

    String randomToken;
    JSONObject token;
    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeProfileRepository employeeProfileRepository;
    @Autowired
    MockMvc mockMvc;

    static Boolean doOnce = true;

    @BeforeEach
    void setUp() throws JSONException {
        randomToken = tokenHelper.tokenCryptBuilder("employee");
        token = new JSONObject();
        token.put("token", randomToken);

        if (doOnce) {
            Token tokenToBeRepo = new Token(randomToken);
            tokenRepository.save(tokenToBeRepo);

            User user = new User();
            user.setUsername("employee");
            user.setPermission("admin");
            userRepository.save(user);

            EmployeeProfile employeeProfile = new EmployeeProfile();
            employeeProfile.setId(1L);
            employeeProfile.setEmployeeNr("1");
            employeeProfile.setRole("employee");
            employeeProfile.setFirstName("One");
            employeeProfile.setLastName("OneOne");
            employeeProfile.setEmail("One@mail.se");
            employeeProfile.setPhoneNr("070111111");

            EmployeeProfile employeeProfileTwo = new EmployeeProfile();
            employeeProfileTwo.setId(2L);
            employeeProfileTwo.setEmployeeNr("2");
            employeeProfileTwo.setRole("employeeTwo");
            employeeProfileTwo.setFirstName("Two");
            employeeProfileTwo.setLastName("TwoTwo");
            employeeProfileTwo.setEmail("Two@mail.se");
            employeeProfileTwo.setPhoneNr("0702222222");

            employeeProfileRepository.save(employeeProfile);
            employeeProfileRepository.save(employeeProfileTwo);

            doOnce = false;
        }
    }

    @Test
    void get_employees_list() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/employeelist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void add_employee() throws Exception {

        JSONObject employee = new JSONObject();
        employee.put("firstName", "pelle");

        JSONObject jsonRequestObject = new JSONObject();
        jsonRequestObject.put("token", randomToken);
        jsonRequestObject.put("employee", employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/employee_add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void remove_employee() throws Exception {

        JSONObject jsonRequestObject = new JSONObject();
        jsonRequestObject.put("employeeid", 1L);
        jsonRequestObject.put("token", randomToken);

        JSONObject responseObject = new JSONObject();
        responseObject.put("employeeid", 1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/employee_remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseObject.toString()));
    }

    @Test
    void update_employee() throws Exception {

        JSONObject employee = new JSONObject();
        employee.put("id", 2L);
        employee.put("firstName", "kalle");
        employee.put("lastName", "svanslös");

        JSONObject jsonRequestObject = new JSONObject();
        jsonRequestObject.put("employee", employee);
        jsonRequestObject.put("token", randomToken);

        JSONObject responseJsonObject = new JSONObject();
        responseJsonObject.put("employeeid", 2L);

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/employee_update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseJsonObject.toString()));
    }
}