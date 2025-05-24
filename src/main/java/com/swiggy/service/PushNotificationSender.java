package com.swiggy.service;

import org.springframework.stereotype.Service;

@Service("PUSH")
public class PushNotificationSender implements NotificationSender {
    @Override
    public void send(String partnerId, String orderId) throws Exception {
        System.out.println("Push notification sent to Partner " + partnerId + " for Order " + orderId);
    }
}