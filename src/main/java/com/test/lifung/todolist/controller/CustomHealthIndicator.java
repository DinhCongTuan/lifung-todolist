package com.test.lifung.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * access GET http://localhost:8080/actuator/health to check the health
     */
    @Override
    public Health health() {
        // Check the status of the H2 database
        boolean databaseStatus = checkDatabaseStatus();

        if (databaseStatus) {
            return Health.up().withDetail("message", "Database is running").build();
        } else {
            return Health.down().withDetail("message", "Database is not accessible").build();
        }
    }

    private boolean checkDatabaseStatus() {
        try {
            // Execute a simple query to check the status of the H2 database
            jdbcTemplate.queryForObject("SELECT 1 FROM DUAL", Integer.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}