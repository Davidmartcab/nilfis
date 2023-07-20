package com.nilfis.nilfis.api.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class CustomerRequest implements Serializable {
    private UUID id;

    @Size(min = 4, max = 50, message = "The name must have from 4 characters up to 50")
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email must have a valid format")
    @Pattern(regexp = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", message = "Email must have a valid format")
    private String email;

    @Size(min = 9, max = 12, message = "The phone must have from 9 characters up to 12")
    @NotBlank(message = "Phone is required")
    private String phone;

    @Size(min = 3, max = 20, message = "The country must have from 4 characters up to 50")
    @NotBlank(message = "Country is required")
    private String country;

}
