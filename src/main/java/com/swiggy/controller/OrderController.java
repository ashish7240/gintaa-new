package com.swiggy.controller;

import com.swiggy.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController{

    private final NotificationService service;

    @Autowired
    public OrderController(NotificationService service) {
        this.service = service;
    }

    @PostMapping("/assign")
    public String assignOrder(@RequestParam("partnerId") String partnerId,
                              @RequestParam("orderId") String orderId,
                              @RequestParam(value = "type", defaultValue = "PUSH") String type) {
        service.scheduleNotification(partnerId, orderId, type);
        return "Order assigned. Notification will be sent in 2 minutes.";
    }
}
