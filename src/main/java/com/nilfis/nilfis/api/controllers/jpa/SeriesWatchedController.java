package com.nilfis.nilfis.api.controllers.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.SeriesWatchedRequest;
import com.nilfis.nilfis.api.models.responses.jpa.CountSeriesResponse;
import com.nilfis.nilfis.api.models.responses.jpa.SeriesByCustomerResponse;
import com.nilfis.nilfis.api.models.responses.jpa.SeriesWatchedResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.jpa.ISeriesWatchedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping(path = "seriesW")
@AllArgsConstructor
@Tag(name = "Series Watched")
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
