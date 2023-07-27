package org.top.ordersapiapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.top.ordersapiapp.dto.Client;
import org.top.ordersapiapp.service.client.ClientService;

import java.util.Optional;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    // 1. добавить клиента: POST /client + json body
    @PostMapping("")
    public Client add(@RequestBody Client client) {
        return clientService.register(client);
    }

    // 2. получить всех клиентов: GET /client
    @GetMapping("")
    public Iterable<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Client> getById(@PathVariable Integer id){
        return clientService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Optional<Client> delete(@PathVariable Integer id){
       return clientService.delete(id);
    }

    @PatchMapping("")
    public Optional<Client> update(@RequestBody Client client){
        return clientService.update(client);
    }
}

