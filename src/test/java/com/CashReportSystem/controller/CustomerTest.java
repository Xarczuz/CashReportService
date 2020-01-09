package com.CashReportSystem.controller;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.CustomerProfile;
import com.CashReportSystem.model.Token;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.CustomerProfileRepository;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.repository.UserRepository;
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
@AutoConfigureMockMvc
@SpringBootTest
public class CustomerTest {

    JSONObject token;
    String randomToken;
    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    private MockMvc mvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerProfileRepository customerProfileRepository;
    @Autowired
    TokenRepository tokenRepository;
    static Boolean doOnce = true;

    @BeforeEach
    void setUp() throws JSONException {
        randomToken = tokenHelper.tokenCryptBuilder("customer");
        token = new JSONObject();
        token.put("token", randomToken);

        if (doOnce) {

            Token tokenTosave = new Token(randomToken);
            tokenRepository.save(tokenTosave);

            User user = new User();
            user.setPermission("admin");
            user.setUsername("customer");
            userRepository.save(user);

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

            customerProfileRepository.save(customerProfileFirst);
            customerProfileRepository.save(customerProfileTwo);

            doOnce = false;
        }
    }

    @Test
    void getCustomerList() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/customer/customerlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void addCustomer() throws Exception {
        
        JSONObject customer = new JSONObject();
        customer.put("firstname", "pelle");

        JSONObject jsonRequestObject = new JSONObject();
        jsonRequestObject.put("token", randomToken);
        jsonRequestObject.put("customer", customer);

        mvc.perform(MockMvcRequestBuilders.post("/customer/customer_add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));

        mvc.perform(MockMvcRequestBuilders.post("/customer/customerlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(token.toString()))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void removeCustomer() throws Exception {

        JSONObject jsonRequestObject = new JSONObject();
        jsonRequestObject.put("id", 1L);
        jsonRequestObject.put("token", randomToken);

        mvc.perform(MockMvcRequestBuilders.post("/customer/customer_remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void updateCustomer() throws Exception {

        JSONObject customer = new JSONObject();
        customer.put("id", 2L);
        customer.put("firstName", "kalle");
        customer.put("lastName", "svanslös");

        JSONObject jsonRequestObject = new JSONObject();
        jsonRequestObject.put("customer", customer);
        jsonRequestObject.put("token", randomToken);

        mvc.perform(MockMvcRequestBuilders.post("/customer/customer_update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestObject.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }

}

