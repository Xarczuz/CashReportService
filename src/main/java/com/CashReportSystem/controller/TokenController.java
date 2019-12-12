package com.CashReportSystem.controller;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Perham
 */
@RestController
@RequestMapping(value = "token")
public class TokenController {
    @Autowired
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("validate_token")
    public ResponseEntity<String> validateToken(@RequestBody String tokenJsonObject) {
        try {
            if (tokenService.validateToken(tokenJsonObject)) {
                try {
                    return ResponseEntity.status(HttpStatus.OK).body(tokenService.findUserPermission(tokenJsonObject));
                } catch (NoSuchUserException e) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token is not valid!");
                }
            }
        } catch (NoSuchTokenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token is not valid!");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token is not valid!");
    }

}
