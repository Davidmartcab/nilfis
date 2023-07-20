package com.nilfis.nilfis.api.controllers;

import com.nilfis.nilfis.api.models.requests.FilmsWatchedRequest;
import com.nilfis.nilfis.api.models.responses.CountFilmsResponse;
import com.nilfis.nilfis.api.models.responses.FilmsByCustomerResponse;
import com.nilfis.nilfis.api.models.responses.FilmsWatchedResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.IFilmsWatchedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping(path = "filmsW")
@AllArgsConstructor
@Tag(name = "Films Watched")
public class FilmsWatchedController {

    private IFilmsWatchedService filmsWatchedService;
    @PostMapping
    public ResponseEntity<FilmsWatchedResponse> post(@Valid @RequestBody FilmsWatchedRequest request) {
        return ResponseEntity.ok(filmsWatchedService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<FilmsWatchedResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(filmsWatchedService.read(id));
    }

    @GetMapping
    public ResponseEntity<HashSet<FilmsWatchedResponse>> getAll() {
        return ResponseEntity.ok(filmsWatchedService.read());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.filmsWatchedService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/byFilm/{id}")
    public ResponseEntity<CountFilmsResponse> readByFilmId(@PathVariable UUID id) {
        return ResponseEntity.ok(this.filmsWatchedService.readByFilm(id));
    }

    @GetMapping(path = "/byCustomer/{id}")
    public ResponseEntity<FilmsByCustomerResponse> readByCustomerId(@PathVariable UUID id) {
        return ResponseEntity.ok(this.filmsWatchedService.readByCustomer(id));
    }
}
