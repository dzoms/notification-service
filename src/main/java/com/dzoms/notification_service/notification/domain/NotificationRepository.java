package com.dzoms.notification_service.notification.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {
    List<NotificationEntity> findAllByRecipientId(UUID receiverId);
}
