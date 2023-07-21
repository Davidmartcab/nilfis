package com.nilfis.nilfis.infrastructure.services.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.CustomerRequest;
import com.nilfis.nilfis.api.models.responses.jpa.CustomersResponse;
import com.nilfis.nilfis.domain.entities.jpa.CustomersEntity;
import com.nilfis.nilfis.domain.repositories.jpa.CustomersRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.jpa.ICustomersService;
import com.nilfis.nilfis.util.enums.Tables;
import com.nilfis.nilfis.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class CustomersService implements ICustomersService {

    private final CustomersRepository customersRepository;

    @Override
    public CustomersResponse create(CustomerRequest request) {
        var customerToPersist = CustomersEntity.builder()
                .name(request.getName().toLowerCase())
                .user_id(request.getUser_id().toLowerCase())
                .build();
        var customerPersisted = this.customersRepository.save(customerToPersist);
        return this.entityToResponse(customerPersisted);
    }

    @Override
    public CustomersResponse read(UUID uuid) {
        var customerFromDB = this.customersRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.customers.name()));
        return this.entityToResponse(customerFromDB);
    }

    @Override
    public HashSet<CustomersResponse> read() {
        var customerFromDB = this.customersRepository.findAll();
        var customerForResponse = new HashSet<CustomersResponse>();
        for (CustomersEntity customer : customerFromDB) {
            customerForResponse.add(this.entityToResponse(customer));
        }

        return customerForResponse;
    }

    @Override
    public HashSet<CustomersResponse> readByUserId(String user_id) {
        var customersFromDb = this.customersRepository.findByUserId(user_id);
        var customersForResponse = new HashSet<CustomersResponse>();
        for (CustomersEntity customer : customersFromDb) {
            customersForResponse.add(this.entityToResponse(customer));
        }
        return customersForResponse;
    }

    @Override
    public void delete(UUID uuid) {
        var customerToDelete = this.customersRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.customers.name()));
        this.customersRepository.delete(customerToDelete);
    }

    private CustomersResponse entityToResponse(CustomersEntity entity) {
        var response = new CustomersResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
