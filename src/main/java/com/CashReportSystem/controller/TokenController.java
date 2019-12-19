package com.CashReportSystem.controller;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/validate_token")
    public ResponseEntity<String> validateToken(@RequestBody String tokenJsonObject) {
        try {
            if (tokenService.validateToken(tokenJsonObject)) {
                try {
                    return ResponseEntity.status(HttpStatus.OK).body(tokenService.findUserPermission(tokenJsonObject));
                } catch (NoSuchUserException e) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
                }
            }
        } catch (NoSuchTokenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token is not valid!");
    }

}
