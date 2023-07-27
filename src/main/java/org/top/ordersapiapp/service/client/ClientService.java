package org.top.ordersapiapp.service.client;

import org.springframework.stereotype.Service;
import org.top.ordersapiapp.dto.Client;

import java.util.Optional;

// интерфейс для выполнения операций с сущность Client
@Service
public interface ClientService {
    Client register(Client client);  // регистрация клиента
    Optional<Client> getById(Integer id);     // получить клиента по id
    Iterable<Client> getAll();      // получить всех клиентов
    Optional<Client> delete(Integer id);   // удалить клиента по id
    Optional<Client> update(Client client);   // изменить клиента по id
}
