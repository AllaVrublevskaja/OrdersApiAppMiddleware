package org.top.ordersapiapp.postgres.client;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ordersapiapp.dto.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
}
