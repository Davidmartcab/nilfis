package com.nilfis.nilfis.api.models.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FilmsRequest implements Serializable {
    @Size(min = 4, max = 50, message = "The title must have from 4 characters up to 50")
    @NotBlank(message = "Title is required")
    private String title;

    @Size(min = 4, max = 50, message = "The director must have from 4 characters up to 50")
    @NotBlank(message = "Director is required")
    private String director;

    @Size(min = 4, max = 4, message = "The year must have 4 characters")
    @NotBlank(message = "Year is required")
    private String year;

    private String url;

    @Min(0)
    @Max(100)
    private Integer rate;

    @Size(min = 4, max = 20, message = "The duration must have 4 characters up to 20")
    @NotBlank(message = "Duration is required")
    private String duration;

    @Size(min = 4, max = 20, message = "The subscription type required must have 4 characters up to 20")
    @NotBlank(message = "Subscription type required is required")
    private String subscription_type_required;
}
