package org.top.ordersapiapp.postgres.OrderProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.top.ordersapiapp.dto.OrderProduct;
import org.top.ordersapiapp.service.OrderProduct.OrderProductService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbOrderProductService implements OrderProductService {
    private final OrderProductRepository orderProductRepository;

    @Override
    public OrderProduct register(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public Optional<OrderProduct> getById(Integer id) {
        return orderProductRepository.findById(id);
    }

    @Override
    public List<OrderProduct> getAll() {
        List<OrderProduct> orderProducts = (List<OrderProduct>) orderProductRepository.findAll();
        if(orderProducts.isEmpty()) return null;
        return orderProducts;
    }

    @Override
    public Optional<OrderProduct> delete(Integer id) {
        Optional<OrderProduct> deleted = orderProductRepository.findById(id);
        if(deleted.isPresent()) orderProductRepository.deleteById(id);
        return deleted;
    }

    @Override
    public Optional<OrderProduct> update(OrderProduct orderProduct) {
        if(orderProduct.getId() == null) return Optional.empty();
        Optional<OrderProduct> updated = orderProductRepository.findById(orderProduct.getId());
        if(updated.isPresent())
            return Optional.of(orderProductRepository.save(orderProduct));
        return Optional.empty();
    }
}

