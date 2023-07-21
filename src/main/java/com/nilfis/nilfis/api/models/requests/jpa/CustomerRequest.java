package com.nilfis.nilfis.api.models.requests.jpa;

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
public class CustomerRequest implements Serializable {
    @Size(min = 4, max = 50, message = "The name must have from 4 characters up to 50")
    @NotBlank(message = "Name is required")
    private String name;

    @Size(min = 4, max = 50, message = "The user id must have from 4 characters up to 50")
    @NotBlank(message = "User Id is required")
    private String user_id;

}
