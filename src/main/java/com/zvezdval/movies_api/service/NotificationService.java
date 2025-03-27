package com.zvezdval.movies_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Async
    public void sendWelcomeMessage(String email) {
        logger.info("Preparing welcome message for {}", email);
        try {
            Thread.sleep(2000); // delay effect
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Interrupted!", e);
        }
        logger.info("Welcome message sent to {}", email);
    }

    @Async
    public void sendLoginMessage(String email) {
        logger.info("Preparing login message for {}", email);
        try {
            Thread.sleep(2000); // delay effect
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Interrupted!", e);
        }
        logger.info("Login message sent to {}", email);
    }
}