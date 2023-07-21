package com.nilfis.nilfis.api.models.requests.mongo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class AppUserRequest implements Serializable {
    @Size(min = 4, max = 50, message = "The name must have from 4 characters up to 50")
    private String username;
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Email is not valid")
    private String email;
    @Size(min = 4, max = 50, message = "The dni must have from 4 characters up to 50")
    private String dni;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password is not valid")
    private String password;
    @Size(min = 9, max = 12, message = "The phone must have from 9 characters up to 12")
    @NotBlank(message = "Phone is required")
    private String phone;
    @Size(min = 3, max = 20, message = "The country must have from 4 characters up to 50")
    @NotBlank(message = "Country is required")
    private String country;
}
