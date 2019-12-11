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

    JSONObject userOne;
    JSONObject token;
    JSONObject employee_list;
    JSONObject customer_list;
    JSONObject responseObject;

    @Autowired
    private MockMvc mvc;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TokenHelper tokenHelper;

    @BeforeEach
    void setUp() throws JSONException {
        userOne = new JSONObject();
        userOne.put("username", "UserOne");
        userOne.put("password", "12345");

        token = new JSONObject();
        token.put("token", tokenHelper.tokenBuilder("UserOne"));
        //token.put("permission", "admin");

        customer_list = new JSONObject();
        customer_list.put("id", "1")
                .put("phoneNr", "0731 765 432");

        employee_list = new JSONObject();
        employee_list.put("id", "1")
                .put("employeeNr", "1")
                .put("phoneNr", "0731 234 567");

        Report report1 = new Report();
        report1.setId((long) 1);
        report1.setReportNr("1");
        report1.setTableName("Bar 1");
        report1.setLocation("Stockholm");
        report1.setEmployeeList(employee_list.toString());
        report1.setEmployeeSign("0731 234 567");
        report1.setCustomerSign(customer_list.toString());
        report1.setDigitalCashFlow((double) 500);
        report1.setCashFlow((double) 700);
        report1.setRevenue((double) 1200);
        report1.setPayment((double) 700);
        report1.setInfoField("Bra kväll.");
        report1.setStatus("Avslutad rapprot");


        Report report2 = new Report();
        report2.setId((long) 2);
        report2.setReportNr("2");
        report2.setTableName("Bar 2");
        report2.setLocation("Stockholm 2");
        report2.setEmployeeList(employee_list.toString());
        report2.setEmployeeSign("0731 234 567");
        report2.setCustomerSign(customer_list.toString());
        report2.setDigitalCashFlow((double) 700);
        report2.setCashFlow((double) 800);
        report2.setRevenue((double) 1500);
        report2.setPayment((double) 700);
        report2.setInfoField("Ok kväll");
        report2.setStatus("Avslutad rapport");

        responseObject = new JSONObject();
        JSONArray reportListJsonArray = new JSONArray();
        responseObject.put("reportlist", reportListJsonArray);
        reportListJsonArray.put(report1);
        reportListJsonArray.put(report2);

        reportRepository.save(report1);
        reportRepository.save(report2);
    }

    @Test
    void get_report_list() throws Exception {
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
