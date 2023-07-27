package org.top.ordersapiapp.service.order;

import org.springframework.stereotype.Service;
import org.top.ordersapiapp.dto.Order;
import org.top.ordersapiapp.response.OrderProductsResponse;
import org.top.ordersapiapp.response.OrderReceiptResponse;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    Order register(Order order);
    Optional<Order> getById(Integer id);
    List<Order> getAll();
    Optional<Order> delete(Integer id);
    Optional<Order> update(Order order);
    OrderReceiptResponse OrderReceipt(Integer id);
    OrderProductsResponse OrderProducts(Integer id);
}
