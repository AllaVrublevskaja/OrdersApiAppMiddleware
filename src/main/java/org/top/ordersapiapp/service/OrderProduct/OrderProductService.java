package org.top.ordersapiapp.service.OrderProduct;

import org.top.ordersapiapp.dto.OrderProduct;

import java.util.List;
import java.util.Optional;

public interface OrderProductService {
    OrderProduct register(OrderProduct orderProduct);
    Optional<OrderProduct> getById(Integer id);
    List<OrderProduct> getAll();
    Optional<OrderProduct> delete(Integer id);
    Optional<OrderProduct> update(OrderProduct orderProduct);
}
