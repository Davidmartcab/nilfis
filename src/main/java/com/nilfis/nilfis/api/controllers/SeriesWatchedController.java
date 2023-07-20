package com.nilfis.nilfis.api.controllers;

import com.nilfis.nilfis.api.models.requests.SeriesWatchedRequest;
import com.nilfis.nilfis.api.models.responses.CountSeriesResponse;
import com.nilfis.nilfis.api.models.responses.SeriesByCustomerResponse;
import com.nilfis.nilfis.api.models.responses.SeriesWatchedResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.ISeriesWatchedService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping(path = "seriesW")
@AllArgsConstructor
public class SeriesWatchedController {

    private ISeriesWatchedService seriesWatchedService;

    @PostMapping
    public ResponseEntity<SeriesWatchedResponse> post(@Valid @RequestBody SeriesWatchedRequest request) {
        return ResponseEntity.ok(seriesWatchedService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<SeriesWatchedResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(seriesWatchedService.read(id));
    }

    @GetMapping
    public ResponseEntity<HashSet<SeriesWatchedResponse>> getAll() {
        return ResponseEntity.ok(seriesWatchedService.read());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.seriesWatchedService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/bySerie/{id}")
    public ResponseEntity<CountSeriesResponse> readBySeriesId(@PathVariable UUID id) {
        return ResponseEntity.ok(this.seriesWatchedService.readBySerie(id));
    }

    @GetMapping(path = "/byCustomer/{id}")
    public ResponseEntity<SeriesByCustomerResponse> readByCustomerId(@PathVariable UUID id) {
        return ResponseEntity.ok(this.seriesWatchedService.readByCustomer(id));
    }

}
