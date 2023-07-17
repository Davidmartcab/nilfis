package com.nilfis.nilfis.infrastructure.abstract_service;

import com.nilfis.nilfis.api.models.requests.CustomerRequest;
import com.nilfis.nilfis.api.models.responses.CustomersResponse;

import java.util.HashSet;
import java.util.UUID;

public interface ICustomersService extends CrudService<CustomerRequest, CustomersResponse, UUID> {
    CustomersResponse readByEmail(String email);
}
