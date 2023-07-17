package com.nilfis.nilfis.api.controllers;

import com.nilfis.nilfis.api.models.requests.SeriesRequest;
import com.nilfis.nilfis.api.models.responses.SeriesResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.ISeriesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping(path = "series")
@AllArgsConstructor
public class SeriesController {

    private ISeriesService seriesService;

    @PostMapping
    public ResponseEntity<SeriesResponse> post(@RequestBody SeriesRequest request) {
        return ResponseEntity.ok(seriesService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<SeriesResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(seriesService.read(id));
    }

    @GetMapping
    public ResponseEntity<HashSet<SeriesResponse>> getAll() {
        return ResponseEntity.ok(seriesService.read());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        seriesService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/sbsType/{type}")
    public ResponseEntity<HashSet<SeriesResponse>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(seriesService.readBySubscriptionsType(type));
    }

    @GetMapping(path = "rateG/{rate}")
    public ResponseEntity<HashSet<SeriesResponse>> getByRateGreater(@PathVariable int rate) {
        return ResponseEntity.ok(seriesService.readByRateGreater(rate));
    }

    @GetMapping(path = "rateL/{rate}")
    public ResponseEntity<HashSet<SeriesResponse>> getByRateLower(@PathVariable int rate) {
        return ResponseEntity.ok(seriesService.readByRateLower(rate));
    }

    @GetMapping(path = "rateB/{minRate}/{maxRate}")
    public ResponseEntity<HashSet<SeriesResponse>> getByRateBetween(@PathVariable int minRate, @PathVariable int maxRate) {
        return ResponseEntity.ok(seriesService.readByRateBetween(minRate, maxRate));
    }

    @GetMapping(path = "title/{title}")
    public ResponseEntity<HashSet<SeriesResponse>> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(seriesService.readByTitleStart(title));
    }

    @GetMapping(path = "director/{director}")
    public ResponseEntity<HashSet<SeriesResponse>> getByDirector(@PathVariable String director) {
        return ResponseEntity.ok(seriesService.readByDirectorStart(director));
    }

    @GetMapping(path = "year/{startYear}/{endYear}")
    public ResponseEntity<HashSet<SeriesResponse>> getByYear(@PathVariable String startYear, @PathVariable String endYear) {
        return ResponseEntity.ok(seriesService.readByYear(startYear, endYear));
    }
}
