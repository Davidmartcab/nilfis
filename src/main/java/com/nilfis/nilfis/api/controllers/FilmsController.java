package com.nilfis.nilfis.api.controllers;

import com.nilfis.nilfis.api.models.requests.FilmsRequest;
import com.nilfis.nilfis.api.models.requests.FilterRequest;
import com.nilfis.nilfis.api.models.responses.FilmsResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.IFilmsPagedService;
import com.nilfis.nilfis.infrastructure.abstract_service.IFilmsService;
import com.nilfis.nilfis.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(path = "films")
@AllArgsConstructor
public class FilmsController {

    private IFilmsService filmsService;
    private IFilmsPagedService filmsPagedService;

    @PostMapping(path = "/new")
    public ResponseEntity<FilmsResponse> post(@RequestBody FilmsRequest request) {
        return ResponseEntity.ok(filmsService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<FilmsResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(filmsService.read(id));
    }

    @PostMapping
    public ResponseEntity<Page<FilmsResponse>> readAll(
            @RequestHeader(required = false, defaultValue = "0") Integer page,
            @RequestHeader(required = false, defaultValue = "10") Integer size,
            @RequestHeader(required = false) SortType sortType,
            @RequestBody(required = false) FilterRequest request
    ) {
        if(Objects.isNull(sortType)) sortType = SortType.NONE;
        if(Objects.isNull(request)){
            var response = this.filmsPagedService.readAll(page, size, sortType);
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
        }else {
            var response = this.filmsPagedService.readAllFiltered(page, size, sortType, request);
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.filmsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
