package com.nilfis.nilfis.api.controllers;

import com.nilfis.nilfis.api.models.responses.HealthResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "health")
@AllArgsConstructor
@Tag(name = "Health")

public class HealthController {
    @GetMapping()
    public ResponseEntity<HealthResponse> getHealth() {
        var response = HealthResponse.builder()
                .status("Ok")
                .message("Application is running.")
                .build();
        return ResponseEntity.ok(response);
    }
}
