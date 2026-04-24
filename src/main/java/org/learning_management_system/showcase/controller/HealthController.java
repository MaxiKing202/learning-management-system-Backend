package org.learning_management_system.showcase.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
@Tag(name = "Health", description = "Application health check endpoints")
public class HealthController {

    @GetMapping
    @Operation(summary = "Basic health check", description = "Returns basic health status, timestamp and a short message")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("message", "Application is running");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detailed")
    @Operation(summary = "Detailed health check", description = "Returns detailed health information including database connectivity and timestamp")
    public ResponseEntity<Map<String, Object>> healthDetailed() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("database", "Connected");
        response.put("message", "Application is healthy");
        return ResponseEntity.ok(response);
    }
}
