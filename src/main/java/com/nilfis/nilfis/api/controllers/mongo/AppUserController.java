package com.nilfis.nilfis.api.controllers.mongo;

import com.nilfis.nilfis.api.models.requests.mongo.AppUserRequest;
import com.nilfis.nilfis.api.models.responses.mongo.AppUserResponse;
import com.nilfis.nilfis.infrastructure.abstract_service.mongo.ModifyUserService;
import com.nilfis.nilfis.util.enums.RolesEmun;
import com.nilfis.nilfis.util.exceptions.BadRole;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
@Tag(name = "User")
public class AppUserController {

    private ModifyUserService modifyUserService;

    @GetMapping(path = "enabled-or-disabled")
    public ResponseEntity<AppUserResponse> enabledOrDisabled(@RequestHeader String email) {
        return ResponseEntity.ok(this.modifyUserService.enabled(email));
    }

    @GetMapping(path = "add-role")
    public ResponseEntity<AppUserResponse> addRole(@RequestHeader String email, @RequestHeader String role) {
        var roleEnum = RolesEmun.ROLE_USER;
        try {
            roleEnum = RolesEmun.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new BadRole();
        }
        return ResponseEntity.ok(this.modifyUserService.addRole(email, roleEnum));
    }

    @GetMapping(path = "remove-role")
    public ResponseEntity<AppUserResponse> removeRole(@RequestHeader String email, @RequestHeader String role) {
        var roleEnum = RolesEmun.ROLE_USER;
        try {
            roleEnum = RolesEmun.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new BadRole();
        }
        return ResponseEntity.ok(this.modifyUserService.removeRole(email, roleEnum));
    }

    @PostMapping(path = "new")
    public ResponseEntity<AppUserResponse> createUser(@RequestBody AppUserRequest request) {
        return ResponseEntity.ok(this.modifyUserService.createUser(request));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<AppUserResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.modifyUserService.findById(id));
    }

    @GetMapping(path = "email/{email}")
    public ResponseEntity<AppUserResponse> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(this.modifyUserService.findByEmail(email));
    }
}
