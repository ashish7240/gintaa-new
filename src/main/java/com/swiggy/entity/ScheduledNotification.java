package com.swiggy.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ScheduledNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deliveryPartnerId;
    private String orderId;
    private String type;
    private LocalDateTime triggerTime;
    private boolean sent;
    private int retryCount;

    public ScheduledNotification() {}

    public ScheduledNotification(String deliveryPartnerId, String orderId, String type, LocalDateTime triggerTime) {
        this.deliveryPartnerId = deliveryPartnerId;
        this.orderId = orderId;
        this.type = type;
        this.triggerTime = triggerTime;
        this.sent = false;
        this.retryCount = 0;
    }

    public Long getId() { return id; }
    public String getDeliveryPartnerId() { return deliveryPartnerId; }
    public String getOrderId() { return orderId; }
    public String getType() { return type; }
    public LocalDateTime getTriggerTime() { return triggerTime; }
    public boolean isSent() { return sent; }
    public int getRetryCount() { return retryCount; }

    public void setSent(boolean sent) { this.sent = sent; }
    public void setRetryCount(int retryCount) { this.retryCount = retryCount; }
}