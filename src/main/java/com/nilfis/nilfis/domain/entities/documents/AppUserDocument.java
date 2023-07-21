package com.nilfis.nilfis.domain.entities.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "app_users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AppUserDocument implements Serializable {
    private String id;
    private String username;
    @Indexed(unique = true)
    private String email;
    private String dni;
    private boolean enabled;
    private String password;
    private String phone;
    private String country;
    private Role role;
}
