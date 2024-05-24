package com.dzoms.notification_service.notification.controller;

import com.dzoms.notification_service.notification.domain.NotificationEntity;
import com.dzoms.notification_service.notification.domain.NotificationRepository;
import com.dzoms.notification_service.notification.service.NotificationCreateUseCase;
import com.dzoms.notification_service.notification.service.NotificationFindUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification-service")
@CrossOrigin
public class NotificationController {

    private final NotificationRepository repository;

    @PostMapping("/{id}")
    public NotificationEntity create(final @Valid @PathVariable UUID id, @RequestBody String roomId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NotificationEntity notificationEntity = NotificationEntity.builder()
                .senderId(UUID.fromString(authentication.getName()))
                .recipientId(id)
                .time(LocalDateTime.now())
                .roomId(roomId)
                .build();
        repository.save(notificationEntity);

        return notificationEntity;
    }

    @GetMapping
    public List<NotificationEntity> get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return repository.findAllByRecipientId(UUID.fromString(authentication.getName()));
    }
}
