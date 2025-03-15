package com.danidu.tracking.tracking_service.controller;

import com.danidu.tracking.tracking_service.model.TrackingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracking-system")
public class TestController {

    @GetMapping("/test")
    public String generateTrackingNumber(){
        return "test success";
    }
}
