package com.CashReportSystem.System;

import com.CashReportSystem.controller.TokenController;
import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.repository.UserRepository;
import com.CashReportSystem.security.PassAndSalt;
import com.CashReportSystem.security.PasswordHash;
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

@ActiveProfiles("tests")
@SpringBootTest
@AutoConfigureMockMvc

public class TokenTest {

    JSONObject userOne;
    JSONObject responseObject;
    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private TokenController tokenController;
    @Autowired
    private MockMvc mvc;

    @Autowired
    TokenRepository tokenRepository;

    @BeforeEach
    void setUp() throws JSONException {

        userOne = new JSONObject();
        userOne.put("username", "UserOne");
        userOne.put("password", "12345");

        responseObject = new JSONObject();
        responseObject.put("token", tokenHelper.tokenBuilder("UserOne"));
        responseObject.put("permission", "admin");

        User user = new User();
        user.setUsername("UserOne");
        PassAndSalt passAndSalt = PasswordHash.hashFirstPassword("12345");
        user.setPassword(passAndSalt.getPASSWORD());
        user.setSalt(passAndSalt.getSALT());
        user.setPermission("admin");
        userRepository.save(user);

    }

    @Test
    void validateToken() throws Exception {




        mvc.perform(MockMvcRequestBuilders.post("/validate_token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        String.valueOf(tokenController.validateToken(responseObject.getString("token")))));

    }
}
