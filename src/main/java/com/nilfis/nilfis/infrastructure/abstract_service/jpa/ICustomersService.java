package com.nilfis.nilfis.infrastructure.abstract_service.jpa;

import com.nilfis.nilfis.api.models.requests.jpa.CustomerRequest;
import com.nilfis.nilfis.api.models.responses.jpa.CustomersResponse;

import java.util.HashSet;
import java.util.UUID;

public interface ICustomersService extends CrudService<CustomerRequest, CustomersResponse, UUID> {
    HashSet<CustomersResponse> readByUserId(String user_id);


}
