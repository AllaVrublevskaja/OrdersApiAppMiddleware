package org.top.ordersapiapp.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.top.ordersapiapp.dto.OrderProduct;
import org.top.ordersapiapp.service.OrderProduct.OrderProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales_receipt")
@RequiredArgsConstructor
public class OrderProductController {

    private final OrderProductService orderProductService;

    @PostMapping("")
    public OrderProduct add(@RequestBody OrderProduct orderProduct){
        return orderProductService.register(orderProduct);
    }

    @GetMapping("")
    public List<OrderProduct> getAll(){
         return orderProductService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<OrderProduct> getById(@PathVariable Integer id){
        return orderProductService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Optional <OrderProduct> delete(@PathVariable Integer id){
        return orderProductService.delete(id);
    }

    @PatchMapping("")
    public Optional<OrderProduct> update(@RequestBody OrderProduct orderProduct){
        return orderProductService.update(orderProduct);
    }
}
