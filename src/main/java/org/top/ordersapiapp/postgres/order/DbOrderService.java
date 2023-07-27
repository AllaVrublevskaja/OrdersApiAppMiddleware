package org.top.ordersapiapp.postgres.order;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.top.ordersapiapp.dto.Order;
import org.top.ordersapiapp.dto.OrderProduct;
import org.top.ordersapiapp.postgres.OrderProduct.OrderProductRepository;
import org.top.ordersapiapp.response.OrderProductsResponse;
import org.top.ordersapiapp.response.OrderReceiptResponse;
import org.top.ordersapiapp.response.Products;
import org.top.ordersapiapp.response.Receipt;
import org.top.ordersapiapp.service.order.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
@RequiredArgsConstructor
public class DbOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    @Override
    public Order register(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        if(orders.isEmpty()) return null;
        return orders;
    }
    //    Удалить заказ и расшивку к нему
    @Override
    public Optional<Order> delete(Integer id) {
        Optional <Order> deleted = orderRepository.findById(id);
        if (deleted.isPresent()) {
            Optional<OrderProduct> deletedOp =
                    orderProductRepository.findAllByOrderId(id).stream().findFirst();
            if (deletedOp.isPresent())
                orderProductRepository.deleteAllByOrderId(id);
            orderRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Order> update(Order order) {
        if(order.getId() == null) return Optional.empty();
        Optional<Order> updated = orderRepository.findById(order.getId());
        if(updated.isPresent())
            return Optional.of(orderRepository.save(order));
        return Optional.empty();
    }
    //   Заказ: Чек на продажу
    @Override
    public OrderReceiptResponse OrderReceipt(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow();
        List<OrderProduct> orderProducts = order.getOrderProducts();
        List <Receipt> products = new ArrayList<>();
        double summa =0;
        for(OrderProduct p: orderProducts){
            double cost = p.getProduct().getPrice()*p.getCount();
            summa+=cost;
            products.add(new Receipt(p.getProduct().getId(),p.getProduct().getName(),p.getProduct().getArticle(),
                    p.getProduct().getPrice(),p.getCount(),cost));
        }
        return OrderReceiptResponse.builder()
                .description(order.getDescription())
                .products(products)
                .summa(summa)
                .build();
    }
    //    список товаров по заказу
    @Override
    public OrderProductsResponse OrderProducts(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow();
        List<OrderProduct> orderProducts = order.getOrderProducts();
        List <Products> products = new ArrayList<>();
        for(OrderProduct p: orderProducts){

            products.add(new Products(p.getProduct().getId(),p.getProduct().getName(),p.getProduct().getArticle(),
                    p.getProduct().getPrice(),p.getCount()));
        }
        return OrderProductsResponse.builder()
                .orderId(id)
                .description(order.getDescription())
                .clientName(order.getClient().getName())
                .products(products)
                .build();
    }
}
