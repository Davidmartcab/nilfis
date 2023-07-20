package com.nilfis.nilfis.infrastructure.services;

import com.nilfis.nilfis.api.models.requests.CustomerRequest;
import com.nilfis.nilfis.api.models.responses.CustomersResponse;
import com.nilfis.nilfis.domain.entities.CustomersEntity;
import com.nilfis.nilfis.domain.repositories.CustomersRepository;
import com.nilfis.nilfis.infrastructure.abstract_service.ICustomersService;
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
                .email(request.getEmail().toLowerCase())
                .phone(request.getPhone())
                .country(request.getCountry().toLowerCase())
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
    public CustomersResponse readByEmail(String email) {
        var customersResponse = new CustomersResponse();
        try {
            customersResponse = this.entityToResponse(this.customersRepository.findByEmail(email));
        } catch (Exception e) {
            throw new IdNotFoundException("customers");
        }
        return customersResponse;
    }

    @Override
    public void verifyCustomer(UUID uuid) {
        var customerFromDB = this.customersRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(Tables.customers.name()));
        if(!customerFromDB.isVerified()){
            customerFromDB.setVerified(true);
            this.customersRepository.save(customerFromDB);
        }
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
