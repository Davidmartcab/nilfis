package com.nilfis.nilfis.api.controllers.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.FilterRequest;
import com.nilfis.nilfis.api.models.requests.jpa.SeriesRequest;
import com.nilfis.nilfis.api.models.responses.jpa.SeriesResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.jpa.ISeriesPagedService;
import com.nilfis.nilfis.infrastructure.abstract_service.jpa.ISeriesService;
import com.nilfis.nilfis.util.enums.SortType;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(path = "series")
@AllArgsConstructor
@Tag(name = "Series")
public class SeriesController {

    private final ISeriesService seriesService;
    private final ISeriesPagedService seriesPagedService;

    @PostMapping(path = "/new")
    public ResponseEntity<SeriesResponse> post(@Valid @RequestBody SeriesRequest request) {
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
