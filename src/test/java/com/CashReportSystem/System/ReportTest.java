package com.CashReportSystem.System;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.Report;
import com.CashReportSystem.model.Token;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.ReportRepository;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.repository.UserRepository;
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
class ReportTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    private MockMvc mvc;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TokenHelper tokenHelper;

    @BeforeEach
    void setUp() {
        reportRepository.deleteAll();
        tokenRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Test
    void get_report_list() throws Exception {

        JSONObject userOne = new JSONObject();
        userOne.put("username", "UserOne");
        userOne.put("password", "12345");

        JSONObject token = new JSONObject();
        String stringToken = tokenHelper.tokenBuilder("tarem");
        token.put("token", stringToken);
        Token tokenToBeRepo = new Token(stringToken);
        tokenRepository.save(tokenToBeRepo);

        Report report = new Report();
        report.setTableName("Poker 1");
        report.setLocation("Oslo");
        reportRepository.save(report);
        Report report2 = new Report();
        report.setTableName("Poker 12");
        report.setLocation("Oslos");
        reportRepository.save(report2);

        mvc.perform(MockMvcRequestBuilders.post("/report/reportlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"reportlist\":[\"Report{id=2, tableName='Poker 1', location='Oslo', reportNr='null', employeeList='null', employeeSign='null', customerSign='null', digitalCashFlow=null, cashFlow=null, revenue=null, payment=null, infoField='null', status='null'}\",\"Report{id=3, tableName='null', location='null', reportNr='null', employeeList='null', employeeSign='null', customerSign='null', digitalCashFlow=null, cashFlow=null, revenue=null, payment=null, infoField='null', status='null'}\"]}"));
    }

    @Test
    void report_add() throws Exception {
        User user = new User();
        user.setUsername("tarem");
        user.setPermission("employee");

        userRepository.save(user);

        String stringToken = tokenHelper.tokenBuilder("tarem");

        Token tokenToBeRepo = new Token(stringToken);
        tokenRepository.save(tokenToBeRepo);

        JSONObject report = new JSONObject();
        report.put("tablename", "bordEtt");

        JSONObject jsonRequestObject = new JSONObject();
        jsonRequestObject.put("token", stringToken);
        jsonRequestObject.put("report", report);

        JSONObject responseObject = new JSONObject();
        responseObject.put("reportid", 4);

        mvc.perform(MockMvcRequestBuilders.post("/report/report_add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseObject.toString()));
    }

    @Test
    void report_remove() throws Exception {

        User user = new User();
        user.setUsername("Pelle");
        user.setPermission("admin");
        userRepository.save(user);

        String stringToken = tokenHelper.tokenBuilder("Pelle");
        Token tokenToBeRepo = new Token(stringToken);
        tokenRepository.save(tokenToBeRepo);

        Report report = new Report();
        report.setTableName("Poker 1");
        report.setLocation("Oslo");
        reportRepository.save(report);

        JSONObject jsonRequestObject = new JSONObject();
        jsonRequestObject.put("reportid", 1L);
        jsonRequestObject.put("token", stringToken);

        JSONObject responseObject = new JSONObject();
        responseObject.put("reportid", 1L);

        mvc.perform(MockMvcRequestBuilders.post("/report/report_remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseObject.toString()));
    }

    @Test
    void report_update() {

        /*customer_list = new JSONObject();
        customer_list.put("id", "1")
                .put("phoneNr", "0731 765 432");

        employee_list = new JSONObject();
        employee_list.put("id", "1")
                .put("employeeNr", "1")
                .put("phoneNr", "0731 234 567");*/

    }

}
