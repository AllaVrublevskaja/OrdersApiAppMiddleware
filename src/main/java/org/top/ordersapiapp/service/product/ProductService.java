package org.top.ordersapiapp.service.product;

import org.springframework.stereotype.Service;
import org.top.ordersapiapp.dto.Product;

import java.util.List;
import java.util.Optional;

// интерфейс для выполнения операций с сущность Product

@Service
public interface ProductService {
    Product register(Product product);
    Optional<Product> getById(Integer id);
    List<Product> getAll();
    Optional<Product> delete(Integer id);
    Optional<Product> update(Product product);
}
