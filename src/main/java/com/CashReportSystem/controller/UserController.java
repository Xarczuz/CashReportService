package com.CashReportSystem.controller;

import com.CashReportSystem.exception.NoReportException;
import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NoPermissionException;

@RestController
@RequestMapping("user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("userlist")
    public ResponseEntity<String> getUsersList(@RequestBody String tokenObject) {
        try {
            String responseObject = userService.getAllUsers(tokenObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("user_add")
    public ResponseEntity<String> createUser(@RequestBody String jsonObject) {
        try {
            String responseObject = userService.createUser(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoSuchTokenException | NoPermissionException | NoSuchUserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("user_remove")
    public ResponseEntity<String> removeUser(@RequestBody String jsonObject) {
        try {
            String responseObject = userService.removeUserByID(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);

        } catch (NoSuchTokenException | NoPermissionException | NoReportException | NoSuchUserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("user_update")
    public ResponseEntity<String> updateUser(@RequestBody String jsonObject) {
        try {
            String responseObject = userService.updateByUserId(jsonObject);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } catch (NoReportException | NoSuchTokenException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
