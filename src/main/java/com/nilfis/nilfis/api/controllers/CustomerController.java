package com.nilfis.nilfis.api.controllers;

import com.nilfis.nilfis.api.models.requests.CustomerRequest;
import com.nilfis.nilfis.api.models.responses.CustomersResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.ICustomersService;
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

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<CustomersResponse> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customersService.readByEmail(email));
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

    @GetMapping(path = "/verify/{id}")
    public ResponseEntity<CustomersResponse> verifyCustomer(@PathVariable UUID id) {
        this.customersService.verifyCustomer(id);
        return ResponseEntity.noContent().build();
    }
}