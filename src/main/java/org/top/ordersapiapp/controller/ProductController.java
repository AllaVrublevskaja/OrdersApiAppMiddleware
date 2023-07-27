package org.top.ordersapiapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.top.ordersapiapp.dto.Product;
import org.top.ordersapiapp.service.product.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("")
    public Product add(@RequestBody Product product) {
        return productService.register(product);
    }

    @GetMapping("")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getById(@PathVariable Integer id){
        return productService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Optional<Product> delete(@PathVariable Integer id){
        return productService.delete(id);
    }

    @PatchMapping("")
    public Optional<Product> update(@RequestBody Product product){
        return productService.update(product);
    }
}
