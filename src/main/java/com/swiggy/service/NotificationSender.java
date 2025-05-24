package com.swiggy.service;

public interface NotificationSender {
    void send(String partnerId, String orderId) throws Exception;
}