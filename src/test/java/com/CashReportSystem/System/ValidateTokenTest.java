package com.CashReportSystem.System;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.Token;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.repository.UserRepository;
import com.CashReportSystem.security.PassAndSalt;
import com.CashReportSystem.security.PasswordHash;
import org.json.JSONObject;
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

public class ValidateTokenTest {
    JSONObject responseObject;
    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenRepository tokenrepository;
    @Autowired
    private MockMvc mvc;

    @Test
    void validateToken() throws Exception {

        responseObject = new JSONObject();
        String token = tokenHelper.tokenBuilder("UserOne");

        responseObject.put("permission", "admin");
        responseObject.put("username", "UserOne");

        JSONObject requestObject = new JSONObject();
        requestObject.put("token", token);
        User user = new User();
        user.setUsername("UserOne");
        PassAndSalt passAndSalt = PasswordHash.hashFirstPassword("12345");
        user.setPassword(passAndSalt.getPASSWORD());
        user.setSalt(passAndSalt.getSALT());
        user.setPermission("admin");
        User userReturned = userRepository.save(user);

        Token tokenToSave = new Token();
        tokenToSave.setToken(token);
        tokenToSave.setUserid(userReturned.getId());
        tokenrepository.save(tokenToSave);

        mvc.perform(MockMvcRequestBuilders.post("/token/validate_token").contentType(MediaType.APPLICATION_JSON)
                .content(requestObject.toString())).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseObject.toString()));
    }
}
