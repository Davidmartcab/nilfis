package com.nilfis.nilfis.api.controllers.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.SubscriptionsTypesRequest;
import com.nilfis.nilfis.api.models.responses.jpa.SubscriptionsTypesResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.jpa.ISubscriptionsTypesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping(path = "sbsTypes")
@AllArgsConstructor
@Tag(name = "Subscriptions Types")
public class SubscriptionsTypesController {

    private final ISubscriptionsTypesService subscriptionsTypesService;

    @PostMapping
    public ResponseEntity<SubscriptionsTypesResponse> post(@Valid @RequestBody SubscriptionsTypesRequest request) {
        return ResponseEntity.ok(subscriptionsTypesService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<SubscriptionsTypesResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(subscriptionsTypesService.read(id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.subscriptionsTypesService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<HashSet<SubscriptionsTypesResponse>> getAll() {
        return ResponseEntity.ok(subscriptionsTypesService.read());
    }
}
