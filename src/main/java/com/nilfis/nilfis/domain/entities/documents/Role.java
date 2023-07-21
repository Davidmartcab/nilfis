package com.nilfis.nilfis.domain.entities.documents;

import com.nilfis.nilfis.util.enums.RolesEmun;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Role {
    @Field(name = "granted_authorities")
    private List<RolesEmun> grantedAuthorities;
}
