package com.CashReportSystem.System;

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

    JSONObject reportList;
    JSONObject responseObject;
    JSONObject employee_list;
    JSONObject customer_list;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() throws JSONException {
        customer_list = new JSONObject();
        customer_list.put("id","1")
                .put("phoneNr","0731 765 432");

        employee_list = new JSONObject();
        employee_list.put("id", "1")
                .put("employeeNr", "1")
                .put("phoneNr", "0731 234 567");

        reportList = new JSONObject();
        reportList.put("id", "1")
                .put("tableName", "Bar 1")
                .put("location", "Stockholm")
                .put("reportNr", "1")
                .put("employeeList", employee_list)
                .put("employeeSign", "0731 234 567")
                .put("customerSign", customer_list)
                .put("digitalCashFlow","500")
                .put("cashFlow","700")
                .put("revenue","1200")
                .put("payment","700")
                .put("infoField","Bra kväll.")
                .put("status","Avslutad rapport.");
    }

    @Test
    void get_report_list() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/reportlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reportList.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(reportList.toString()));
    }

    @Test
    void report_add() {

    }

    @Test
    void report_update() {

    }

    @Test
    void report_remove() {

    }

}
