package com.nilfis.nilfis.api.controllers.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.CustomerRequest;
import com.nilfis.nilfis.api.models.responses.jpa.CustomersResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.jpa.ICustomersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping(path = "customers")
@AllArgsConstructor
@Tag(name = "Customers")
public class CustomerController {

    private final ICustomersService customersService;

    @PostMapping
    public ResponseEntity<CustomersResponse> post(@Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customersService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CustomersResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(customersService.read(id));
    }

    @GetMapping(path = "/userId/{userId}")
    public ResponseEntity<HashSet<CustomersResponse>> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(customersService.readByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<HashSet<CustomersResponse>> getAll() {
        return ResponseEntity.ok(customersService.read());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.customersService.delete(id);
        return ResponseEntity.noContent().build();
    }
}