package com.epam.aggregatebackupqueue.executor;

import com.epam.aggregatebackupqueue.aggregate.Order;
import com.epam.aggregatebackupqueue.commands.CreateOrderCommand;
import com.epam.aggregatebackupqueue.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "create-order-command-executor")
public class CreateOrderCommandExecutor extends AbstractCommandExecutor<CreateOrderCommand, Order>{
    private final OrderRepository orderRepository;

    @Autowired
    public CreateOrderCommandExecutor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order execute(CreateOrderCommand createOrderCommand) {
        return orderRepository.save(Order.builder().customerId("customer-1").build());
    }
}
