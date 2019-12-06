package com.CashReportSystem.System;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.User;
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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class LoginTest {

    JSONObject userOne;
    JSONObject responseObject;

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

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
    void login_username_and_password_is_correct() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userOne.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseObject.toString()));
    }

    @Test
    void login_username_or_password_is_incorrect() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/login/").contentType(MediaType.APPLICATION_JSON)
                .content(userOne.toString())).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testTobias(){
        System.out.println(userOne);
        System.out.println(responseObject);
    }

}
