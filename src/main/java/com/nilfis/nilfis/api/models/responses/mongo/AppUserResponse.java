package com.nilfis.nilfis.api.models.responses.mongo;

import com.nilfis.nilfis.domain.entities.documents.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AppUserResponse {
    private String id;
    private String username;
    private String email;
    private String dni;
    private boolean enabled;
    private String phone;
    private String country;
    private Role role;
}
