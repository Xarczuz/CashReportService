package com.CashReportSystem.System;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.Report;
import com.CashReportSystem.repository.ReportRepository;
import org.json.JSONArray;
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
class ReportTest {

    JSONObject employee_list;
    JSONObject customer_list;

    @Autowired
    private MockMvc mvc;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TokenHelper tokenHelper;

    @BeforeEach
    void setUp() throws JSONException {

        customer_list = new JSONObject();
        customer_list.put("id", "1")
                .put("phoneNr", "0731 765 432");

        employee_list = new JSONObject();
        employee_list.put("id", "1")
                .put("employeeNr", "1")
                .put("phoneNr", "0731 234 567");

    }

    @Test
    void get_report_list() throws Exception {
        JSONObject userOne = new JSONObject();
        userOne.put("username", "UserOne");
        userOne.put("password", "12345");

        JSONObject token = new JSONObject();
        token.put("token", tokenHelper.tokenBuilder("UserOne"));

        JSONObject responseObject = new JSONObject();
        JSONArray reportListJsonArray = new JSONArray();
        responseObject.put("reportlist", reportListJsonArray);

        mvc.perform(MockMvcRequestBuilders.post("/report/reportlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseObject.toString()));
    }

    @Test
    void report_add() throws Exception {
      /*  mvc.perform(MockMvcRequestBuilders.post("/report/report_add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(report.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(reportAdd.toString()));*/
    }

    @Test
    void report_update() {

    }

    @Test
    void report_remove() {

    }

}
