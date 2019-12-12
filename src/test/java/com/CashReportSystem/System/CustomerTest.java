package com.CashReportSystem.System;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.CustomerProfile;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.CustomerProfileRepository;
import com.CashReportSystem.repository.UserRepository;
import org.json.JSONArray;
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
@AutoConfigureMockMvc
@SpringBootTest
public class CustomerTest {
    JSONObject token;
    JSONObject responseObject;

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerProfileRepository customerProfileRepository;

    @Test
    void getCustomerList() throws Exception {

        String randomToken = tokenHelper.tokenBuilder("admin1");

        User user = new User();
        user.setPermission("admin");
        user.setUsername("admin1");
        userRepository.save(user);

        token = new JSONObject();
        token.put("token", randomToken);

        CustomerProfile customerProfileFirst = new CustomerProfile();

        customerProfileFirst.setId((long) 1);
        customerProfileFirst.setCompanyName("employee");
        customerProfileFirst.setFirstName("One");
        customerProfileFirst.setLastName("OneOne");
        customerProfileFirst.setEmail("One@mail.se");
        customerProfileFirst.setPhoneNr("070111111");

        CustomerProfile customerProfileTwo = new CustomerProfile();

        customerProfileTwo.setId((long) 2);
        customerProfileTwo.setCompanyName("Adidas");
        customerProfileTwo.setFirstName("Two");
        customerProfileTwo.setLastName("TwoTwo");
        customerProfileTwo.setEmail("Two@mail.se");
        customerProfileTwo.setPhoneNr("076151111");

        responseObject = new JSONObject();
        JSONArray customerJsonArray = new JSONArray();

        responseObject.put("customerlist", customerJsonArray);
        customerJsonArray.put(customerProfileFirst);
        customerJsonArray.put(customerProfileTwo);

        customerProfileRepository.save(customerProfileFirst);
        customerProfileRepository.save(customerProfileTwo);

        mvc.perform(MockMvcRequestBuilders.post("/customer/customerlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(responseObject.toString()));

    }
}
