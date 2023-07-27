package org.top.ordersapiapp.postgres.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ordersapiapp.dto.Order;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository <Order, Integer> {
    void deleteAllByClientId(Integer clientId);
    List<Order> findAllByClientId(Integer clientId);
}
