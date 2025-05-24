package com.swiggy.repository;

import com.swiggy.entity.ScheduledNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledNotificationRepository extends JpaRepository<ScheduledNotification, Long> {
    List<ScheduledNotification> findBySentFalseAndTriggerTimeBefore(LocalDateTime now);
}