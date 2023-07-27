package org.top.ordersapiapp.postgres.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.top.ordersapiapp.dto.Product;
import org.top.ordersapiapp.service.product.ProductService;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class DbProductService implements ProductService {
    public final ProductRepository productRepository;

    @Override
    public Product register(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = (List<Product>) productRepository.findAll();
        if(products.isEmpty())
            return null;
        return products;
    }

    @Override
    public Optional<Product> delete(Integer id) {
        Optional<Product> deleted = productRepository.findById(id);
        if(deleted.isPresent()) productRepository.deleteById(id);
        return deleted;
    }

    @Override
    public Optional<Product> update(Product product) {
        if(product.getId() == null) return Optional.empty();
        Optional<Product> changed = productRepository.findById(product.getId());
        if(changed.isPresent())
            return Optional.of(productRepository.save(product));
        return Optional.empty();
    }
}
