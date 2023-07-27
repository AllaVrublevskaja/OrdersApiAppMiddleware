package org.top.ordersapiapp.postgres.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.ordersapiapp.dto.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
