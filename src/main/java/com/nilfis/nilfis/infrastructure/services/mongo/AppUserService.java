package com.nilfis.nilfis.infrastructure.services.mongo;

import com.nilfis.nilfis.api.models.requests.jpa.CustomerRequest;
import com.nilfis.nilfis.api.models.requests.mongo.AppUserRequest;
import com.nilfis.nilfis.api.models.responses.jpa.CustomersResponse;
import com.nilfis.nilfis.api.models.responses.mongo.AppUserResponse;
import com.nilfis.nilfis.domain.entities.documents.AppUserDocument;
import com.nilfis.nilfis.domain.entities.documents.Role;
import com.nilfis.nilfis.domain.entities.jpa.CustomersEntity;
import com.nilfis.nilfis.domain.repositories.mongo.AppUserRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.mongo.ModifyUserService;
import com.nilfis.nilfis.infrastructure.services.jpa.CustomersService;
import com.nilfis.nilfis.util.enums.RolesEmun;
import com.nilfis.nilfis.util.exceptions.EmailJustExist;
import com.nilfis.nilfis.util.exceptions.EmailNotExist;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AppUserService implements ModifyUserService {

    private final AppUserRepository appUserRepository;

    private final CustomersService customersService;

    @Override
    public AppUserResponse enabled(String email) {
        var user = this.appUserRepository.findByEmail(email.toLowerCase()).orElseThrow(EmailNotExist::new);
        user.setEnabled(!user.isEnabled());
        var userSaved = this.appUserRepository.save(user);
        return this.entityToResponse(userSaved);
    }

    @Override
    public AppUserResponse addRole(String email, RolesEmun role) {
        var user = this.appUserRepository.findByEmail(email.toLowerCase()).orElseThrow(EmailNotExist::new);
        if (user.getRole().getGrantedAuthorities().contains(role)) {
            return this.entityToResponse(user);
        }
        user.getRole().getGrantedAuthorities().add(role);
        var userSaved = this.appUserRepository.save(user);
        return this.entityToResponse(userSaved);
    }

    @Override
    public AppUserResponse removeRole(String email, RolesEmun role) {
        var user = this.appUserRepository.findByEmail(email.toLowerCase()).orElseThrow(EmailNotExist::new);
        if (!user.getRole().getGrantedAuthorities().contains(role)) {
            return this.entityToResponse(user);
        }
        user.getRole().getGrantedAuthorities().remove(role);
        var userSaved = this.appUserRepository.save(user);

        return this.entityToResponse(userSaved);
    }

    @Override
    public AppUserResponse createUser(AppUserRequest request) {

        var user = this.appUserRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            throw new EmailJustExist();
        }
        var role = Role.builder()
                .grantedAuthorities(List.of(RolesEmun.ROLE_USER))
                .build();
        var userToSave = AppUserDocument.builder()
                .username(request.getUsername().toLowerCase())
                .email(request.getEmail().toLowerCase())
                .dni(request.getDni().toLowerCase())
                .password(request.getPassword())
                .enabled(false)
                .phone(request.getPhone())
                .country(request.getCountry())
                .role(role)
                .build();

        var userSaved = this.appUserRepository.save(userToSave);
        var customerToSave = CustomerRequest.builder()
                .name(request.getUsername().toLowerCase())
                .user_id(userSaved.getId())
                .build();

        this.customersService.create(customerToSave);

        return this.entityToResponse(userSaved);
    }

    @Override
    public AppUserResponse findByEmail(String email) {

        var user = this.appUserRepository.findByEmail(email.toLowerCase()).orElseThrow(EmailNotExist::new);
        return this.entityToResponse(user);
    }

    @Override
    public AppUserResponse findById(String id) {
        var user = this.appUserRepository.findById(id).orElseThrow(EmailNotExist::new);
        return this.entityToResponse(user);
    }

    @Transactional(readOnly = true)
    private void loadByEmail(String email) {
        var user = this.appUserRepository.findByEmail(email.toLowerCase()).orElseThrow(EmailNotExist::new);
    }

    private AppUserResponse entityToResponse(AppUserDocument entity) {
        var response = new AppUserResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
