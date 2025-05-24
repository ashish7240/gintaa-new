package com.swiggy.scheduler;

import com.swiggy.entity.ScheduledNotification;
import com.swiggy.repository.ScheduledNotificationRepository;
import com.swiggy.service.NotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotificationScheduler {

    private final ScheduledNotificationRepository repository;
    private final NotificationService service;

    public NotificationScheduler(ScheduledNotificationRepository repository, NotificationService service) {
        this.repository = repository;
        this.service = service;
    }

    @Scheduled(fixedRate = 60000)
    public void run() {
        List<ScheduledNotification> pendingNotifications =
                repository.findBySentFalseAndTriggerTimeBefore(LocalDateTime.now());

        for (ScheduledNotification notification : pendingNotifications) {
            if (notification.getRetryCount() < 3) {
                service.process(notification);
            } else {
                notification.setSent(true); // optional: mark as failed
                repository.save(notification);
            }
        }
    }
}
