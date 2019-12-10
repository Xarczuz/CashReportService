package com.CashReportSystem.controller;

import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TokenController {
    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/validate_token")
    public ResponseEntity<String> validateToken(@RequestBody String tokenJsonObject) {

        if (tokenService.validateToken(tokenJsonObject)) {

            // System.out.println(tokenHelper.tokenParser(token));

            return ResponseEntity.status(HttpStatus.OK).body(tokenHelper.tokenParser(tokenJsonObject));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token is not valid!");
    }

}
