package com.nilfis.nilfis.api.controllers;

import com.nilfis.nilfis.api.models.requests.CustomerRequest;
import com.nilfis.nilfis.api.models.requests.FilmsRequest;
import com.nilfis.nilfis.api.models.responses.CustomersResponse;
import com.nilfis.nilfis.api.models.responses.FilmsResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.IFilmsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping(path = "films")
@AllArgsConstructor
public class FilmsController {

    private IFilmsService filmsService;
    @PostMapping
    public ResponseEntity<FilmsResponse> post(@RequestBody FilmsRequest request) {
        return ResponseEntity.ok(filmsService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<FilmsResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(filmsService.read(id));
    }

    @GetMapping
    public ResponseEntity<HashSet<FilmsResponse>> getAll() {
        return ResponseEntity.ok(filmsService.read());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.filmsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/sbsType/{type}")
    public ResponseEntity<HashSet<FilmsResponse>> getBytype(@PathVariable String type) {
        return ResponseEntity.ok(filmsService.readBySubscriptionsType(type));
    }

    @GetMapping(path = "rateG/{rate}")
    public ResponseEntity<HashSet<FilmsResponse>> getByRateGrater(@PathVariable int rate) {
        return ResponseEntity.ok(filmsService.readByRateGreater(rate));
    }

    @GetMapping(path = "rateL/{rate}")
    public ResponseEntity<HashSet<FilmsResponse>> getByRateLower(@PathVariable int rate) {
        return ResponseEntity.ok(filmsService.readByRateLower(rate));
    }

    @GetMapping(path = "rateB/{minRate}/{maxRate}")
    public ResponseEntity<HashSet<FilmsResponse>> getByRateBetween(@PathVariable int minRate, @PathVariable int maxRate) {
        return ResponseEntity.ok(filmsService.readByRateBetween(minRate, maxRate));
    }

    @GetMapping(path = "title/{title}")
    public ResponseEntity<HashSet<FilmsResponse>> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(filmsService.readByTitleStart(title));
    }

    @GetMapping(path = "director/{director}")
    public ResponseEntity<HashSet<FilmsResponse>> getByDirector(@PathVariable String director) {
        return ResponseEntity.ok(filmsService.readByDirectorStart(director));
    }

    @GetMapping(path = "year/{startYear}/{endYear}")
    public ResponseEntity<HashSet<FilmsResponse>> getByYear(@PathVariable String startYear, @PathVariable String endYear) {
        return ResponseEntity.ok(filmsService.readByYear(startYear, endYear));
    }
}
