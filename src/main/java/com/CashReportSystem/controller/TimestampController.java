package com.CashReportSystem.controller;

import com.CashReportSystem.repository.TimeStampRepository;
import com.CashReportSystem.service.TimestampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "timestamp")
public class TimestampController {
    @Autowired
    TimestampController timestampController;

    @Autowired
    TimestampService timestampService;

    @Autowired
    TimeStampRepository timeStampRepository;

    @PostMapping("timestamplist")
    public ResponseEntity<String>addTimestamp(@RequestBody String jsonObject){
        //TODO
        return ResponseEntity.status(HttpStatus.OK).body("TODO");
    }




}
