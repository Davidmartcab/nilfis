package com.nilfis.nilfis.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FilmsResponse implements Serializable {
    private UUID id;
    private String title;
    private String director;
    private String year;
    private String url;
    private Integer rate;
    private String duration;
    private String subscription_type_required;
}
