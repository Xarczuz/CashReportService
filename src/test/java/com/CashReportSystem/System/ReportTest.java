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

    static Boolean doOnce = true;

    @BeforeEach
    void setUp() {
        if (doOnce) {
            //Get reportlist
            String stringToken = tokenHelper.tokenBuilder("tarem");
            Token tokenToBeRepo = new Token(stringToken);
            tokenRepository.save(tokenToBeRepo);

            Report report = new Report();
            report.setGameTableName("Poker 1");
            report.setLocation("Oslo");
            reportRepository.save(report);
            Report report2 = new Report();
            report.setGameTableName("Poker 12");
            report.setLocation("Oslos");
            reportRepository.save(report2);

            //Remove report
            User user = new User();
            user.setUsername("Pelle");
            user.setPermission("admin");
            userRepository.save(user);

            stringToken = tokenHelper.tokenBuilder("Pelle");
            tokenToBeRepo = new Token(stringToken);
            tokenRepository.save(tokenToBeRepo);

            report = new Report();
            report.setGameTableName("Poker 1");
            report.setLocation("Oslo");
            reportRepository.save(report);

            //Add report
            user = new User();
            user.setUsername("smartem");
            user.setPermission("employee");
            userRepository.save(user);

            stringToken = tokenHelper.tokenBuilder("smartem");
            tokenToBeRepo = new Token(stringToken);
            tokenRepository.save(tokenToBeRepo);

            //Update report
            user = new User();
            user.setUsername("Kalle");
            user.setPermission("admin");
            userRepository.save(user);

            String token = tokenHelper.tokenBuilder("Kalle");
            Token tokenToRepo = new Token(token);
            tokenRepository.save(tokenToRepo);

            report = new Report();
            report.setId(2L);
            report.setGameTableName("BlackJack");
            report.setLocation("Stockholm");
            reportRepository.save(report);

            doOnce = false;
        }
    }

    @Test
    void get_report_list() throws Exception {
        JSONObject token = new JSONObject();
        String stringToken = tokenHelper.tokenBuilder("tarem");
        token.put("token", stringToken);

        JSONObject userOne = new JSONObject();
        userOne.put("username", "UserOne");
        userOne.put("password", "12345");

        mvc.perform(MockMvcRequestBuilders.post("/report/reportlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void report_add() throws Exception {

        JSONObject report = new JSONObject();
        report.put("tablename", "bordEtt");

        String stringToken = tokenHelper.tokenBuilder("smartem");
        JSONObject jsonRequestObject = new JSONObject();
        jsonRequestObject.put("token", stringToken);
        jsonRequestObject.put("report", report);

        mvc.perform(MockMvcRequestBuilders.post("/report/report_add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/report/report_add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/report/report_add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void report_remove() throws Exception {
        String stringToken = tokenHelper.tokenBuilder("Pelle");
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
    void report_update() throws Exception {

        String token = tokenHelper.tokenBuilder("Kalle");
        JSONObject reportJsonObject = new JSONObject();
        reportJsonObject.put("id", 2L);
        reportJsonObject.put("tablename", "Pingis");
        reportJsonObject.put("location", "Helsingborg");

        JSONObject jsonRequestObject = new JSONObject();
        jsonRequestObject.put("report", reportJsonObject);
        jsonRequestObject.put("token", token);

        JSONObject responseJsonObject = new JSONObject();
        responseJsonObject.put("reportid", 2L);

        mvc.perform(MockMvcRequestBuilders.post("/report/report_update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseJsonObject.toString()));
    }

}
