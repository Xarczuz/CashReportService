package com.CashReportSystem.controller;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.Token;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.repository.UserRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void get_user_list() throws Exception {
        User userToDB = new User();
        userToDB.setUsername("tarem");
        userToDB.setPermission("admin");
        userRepository.save(userToDB);

        String stringToken = tokenHelper.tokenCryptBuilder("tarem");
        Token tokenToBeRepo = new Token(stringToken);
        tokenRepository.save(tokenToBeRepo);

        JSONObject token = new JSONObject();
        token.put("token", stringToken);

        JSONObject requestObject = new JSONObject();

        requestObject.put("token", stringToken);

        User user = new User();
        user.setUsername("Petra");
        user.setPermission("GOD");
        requestObject.put("user", user.toJsonObject());

        mvc.perform(MockMvcRequestBuilders.post("/user/user_add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));

        mvc.perform(MockMvcRequestBuilders.post("/user/userlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}