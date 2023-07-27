package org.top.ordersapiapp.postgres.OrderProduct;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ordersapiapp.dto.OrderProduct;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Integer> {
    Optional<OrderProduct> findByOrderId(Integer orderId);
    void deleteAllByOrderId(Integer orderId);
    List<OrderProduct> findAllByOrderId(Integer id);
}
