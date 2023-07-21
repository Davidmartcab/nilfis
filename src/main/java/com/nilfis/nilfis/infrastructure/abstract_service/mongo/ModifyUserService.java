package com.nilfis.nilfis.infrastructure.abstract_service.mongo;

import com.nilfis.nilfis.api.models.requests.mongo.AppUserRequest;
import com.nilfis.nilfis.api.models.responses.mongo.AppUserResponse;
import com.nilfis.nilfis.util.enums.RolesEmun;

import java.util.List;
import java.util.Map;

public interface ModifyUserService {
    AppUserResponse enabled(String email);

    AppUserResponse addRole(String email, RolesEmun role);

    AppUserResponse removeRole(String email, RolesEmun role);

    AppUserResponse createUser(AppUserRequest request);

    AppUserResponse findByEmail(String email);

    AppUserResponse findById(String id);
}
