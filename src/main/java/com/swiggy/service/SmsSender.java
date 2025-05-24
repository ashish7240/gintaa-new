package com.swiggy.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("SMS")
public class SmsSender implements NotificationSender {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.from}")
    private String fromPhoneNumber;

    @Value("${twilio.phone.to}")  // For demo/testing only, in real app use partner's phone
    private String toPhoneNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void send(String partnerId, String orderId) throws Exception {
        String body = "Hello Partner " + partnerId + ", your delivery order " + orderId + " is scheduled.";
        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), body).create();
        System.out.println("Twilio SMS sent to " + toPhoneNumber + " for Order " + orderId);
    }
}