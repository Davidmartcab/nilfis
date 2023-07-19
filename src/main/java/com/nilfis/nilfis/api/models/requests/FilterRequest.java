package com.nilfis.nilfis.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FilterRequest implements Serializable {
    private String type_name;
    private Integer minRate;
    private Integer maxRate;
    private String titleStart;
    private String directorStart;
    private String yearStart;
    private String yearEnd;
}
