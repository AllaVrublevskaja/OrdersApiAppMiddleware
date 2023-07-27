package org.top.ordersapiapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.top.ordersapiapp.dto.Order;
import org.top.ordersapiapp.response.OrderProductsResponse;
import org.top.ordersapiapp.response.OrderReceiptResponse;
import org.top.ordersapiapp.service.order.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    public Order add(@RequestBody Order order){return orderService.register(order);}

    @GetMapping("")
    public List<Order> getAll(){return orderService.getAll();}

    @GetMapping("/{id}")
    public Optional getById(@PathVariable Integer id){
        return orderService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Optional delete(@PathVariable Integer id){return orderService.delete(id);}

    @PatchMapping("")
    public Optional update(@RequestBody Order order){
        return orderService.update(order);
    }

    @GetMapping("/sale/{id}")  // запрос "Чек"
    public OrderReceiptResponse OrderReceipt(@PathVariable Integer id){
        return orderService.OrderReceipt(id);
    }
    @GetMapping("/productlist/{id}")  // запрос "Чек"
    public OrderProductsResponse OrderProducts(@PathVariable Integer id){
        return orderService.OrderProducts(id);
    }
}
