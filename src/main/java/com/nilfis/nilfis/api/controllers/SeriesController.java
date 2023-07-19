package com.nilfis.nilfis.api.controllers;

import com.nilfis.nilfis.api.models.requests.FilterRequest;
import com.nilfis.nilfis.api.models.requests.SeriesRequest;
import com.nilfis.nilfis.api.models.responses.FilmsResponse;
import com.nilfis.nilfis.api.models.responses.SeriesResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.ISeriesPagedService;
import com.nilfis.nilfis.infrastructure.abstract_service.ISeriesService;
import com.nilfis.nilfis.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(path = "series")
@AllArgsConstructor
public class SeriesController {

    private final ISeriesService seriesService;
    private final ISeriesPagedService seriesPagedService;

    @PostMapping(path = "/new")
    public ResponseEntity<SeriesResponse> post(@RequestBody SeriesRequest request) {
        return ResponseEntity.ok(seriesService.create(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<SeriesResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(seriesService.read(id));
    }

    @PostMapping
    public ResponseEntity<Page<SeriesResponse>> readAll(
            @RequestHeader(required = false, defaultValue = "0") Integer page,
            @RequestHeader(required = false, defaultValue = "10") Integer size,
            @RequestHeader(required = false) SortType sortType,
            @RequestBody(required = false) FilterRequest request
    ) {
        if(Objects.isNull(sortType)) sortType = SortType.NONE;
        if(Objects.isNull(request)){
            var response = this.seriesPagedService.readAll(page, size, sortType);
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
        }else {
            var response = this.seriesPagedService.readAllFiltered(page, size, sortType, request);
            return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        seriesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
