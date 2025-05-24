package com.swiggy.service;

import com.swiggy.entity.ScheduledNotification;
import com.swiggy.repository.ScheduledNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class NotificationService {

    @Autowired
    private ScheduledNotificationRepository repository;

    @Autowired
    private Map<String, NotificationSender> senderMap;

    public void scheduleNotification(String partnerId, String orderId, String type) {
        LocalDateTime triggerTime = LocalDateTime.now().plusMinutes(2);
        ScheduledNotification notification = new ScheduledNotification(partnerId, orderId, type.toUpperCase(), triggerTime);
        repository.save(notification);
    }

    public void process(ScheduledNotification n) {
        try {
            senderMap.get(n.getType()).send(n.getDeliveryPartnerId(), n.getOrderId());
            n.setSent(true);
        } catch (Exception e) {
            n.setRetryCount(n.getRetryCount() + 1);
        } finally {
            repository.save(n);
        }
    }
}