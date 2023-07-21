package com.nilfis.nilfis.api.controllers.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.SubscriptionsRequest;
import com.nilfis.nilfis.api.models.responses.jpa.SubscriptionsResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.jpa.ISubscriptionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping(path = "sbs")
@AllArgsConstructor
@Tag(name = "Subscriptions")
public class SubscriptionsController {

    private final ISubscriptionsService subscriptionsService;

    @PostMapping
    public ResponseEntity<SubscriptionsResponse> post(@Valid @RequestBody SubscriptionsRequest request) {
        return ResponseEntity.ok(subscriptionsService.create(request));
    }

    @GetMapping
    public ResponseEntity<HashSet<SubscriptionsResponse>> get() {
        return ResponseEntity.ok(subscriptionsService.read());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<SubscriptionsResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(subscriptionsService.read(id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.subscriptionsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<HashSet<SubscriptionsResponse>> getByCustomerId(@PathVariable String id) {
        return ResponseEntity.ok(subscriptionsService.readByCustomerId(id));
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<Void> deleteByCustomerId(@PathVariable String id) {
        this.subscriptionsService.deleteByCustomerId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/user/active/{id}")
    public ResponseEntity<HashSet<SubscriptionsResponse>> getActiveByCustomerId(@PathVariable String id) {
        return ResponseEntity.ok(subscriptionsService.readActiveByCustomer(id));
    }

    @DeleteMapping(path = "/user/active/{id}")
    public ResponseEntity<Void> deleteExpiredByCustomerId(@PathVariable String id) {
        this.subscriptionsService.deleteExpiredByCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/active")
    public ResponseEntity<Void> deleteAllExpired() {
        this.subscriptionsService.deleteAllExpired();
        return ResponseEntity.noContent().build();
    }

}
