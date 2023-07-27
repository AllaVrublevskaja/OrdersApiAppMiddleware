package org.top.ordersapiapp.postgres.client;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.top.ordersapiapp.dto.Client;
import org.top.ordersapiapp.dto.Order;
import org.top.ordersapiapp.dto.OrderProduct;
import org.top.ordersapiapp.postgres.OrderProduct.OrderProductRepository;
import org.top.ordersapiapp.postgres.order.OrderRepository;
import org.top.ordersapiapp.service.client.ClientService;

import java.util.List;
import java.util.Optional;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
@RequiredArgsConstructor
public class DbClientService implements ClientService {

    private final ClientRepository clientRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderRepository orderRepository;

    @Override
    public Client register(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> getById(Integer id) {
        return clientRepository.findById(id);
    }

    @Override
    public Iterable<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> delete(Integer id) {
        Optional<Client> deleted = clientRepository.findById(id);
        if (deleted.isPresent()) {
            List<Order> deletedOrder = orderRepository.findAllByClientId(id);
            if (!deletedOrder.isEmpty()) {
                for(Order d: deletedOrder) {
                    Optional<OrderProduct> deletedOp = orderProductRepository.findByOrderId(d.getId());
                    if (deletedOp.isPresent()) orderProductRepository.deleteAllByOrderId(d.getId());
                }
                orderRepository.deleteAllByClientId(id);
            }
            clientRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Client> update(Client client) {
        if(client.getId() == null) return Optional.empty();
        Optional<Client> changed = clientRepository.findById(client.getId());
        if (changed.isPresent())
            return Optional.of(clientRepository.save(client));
        return Optional.empty();
    }
}
