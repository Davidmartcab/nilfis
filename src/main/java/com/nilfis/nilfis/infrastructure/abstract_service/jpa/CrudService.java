package com.nilfis.nilfis.infrastructure.abstract_service.jpa;

import java.util.HashSet;

public interface CrudService <RQ, RS, ID>{
    RS create(RQ request);

    RS read(ID id);

    HashSet<RS> read();

    void delete(ID id);
}
