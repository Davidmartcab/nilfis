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
public class SeriesRequest implements Serializable {
    private String title;
    private String director;
    private String year;
    private String url;
    private Integer rate;
    private Integer chapters;
    private String subscription_type_required;
}
