package com.example.SirRegLogin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Logging Service
 * Provides centralized logging functionality.
 */
@Service
public class LoggingService {
    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    public void logInfo(String message, Object... args) {
        logger.info(message, args);
    }

    public void logWarn(String message, Object... args) {
        logger.warn(message, args);
    }

    public void logError(String message, Object... args) {
        logger.error(message, args);
    }
}
