package com.epam.aggregatebackupqueue.executor;

import com.epam.aggregatebackupqueue.aggregate.Order;
import com.epam.aggregatebackupqueue.annotations.AggregateBackup;
import com.epam.aggregatebackupqueue.commands.DeleteOrderCommand;
import com.epam.aggregatebackupqueue.exceptions.OrderNotFoundException;
import com.epam.aggregatebackupqueue.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(value = "delete-order-command-executor")
public class DeleteOrderCommandExecutor extends AbstractCommandExecutor<DeleteOrderCommand, Order>{

    private final OrderRepository orderRepository;

    @Autowired
    public DeleteOrderCommandExecutor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order execute(DeleteOrderCommand deleteOrderCommand) throws OrderNotFoundException {
        Optional<Order> orderOptional = orderRepository.findById(deleteOrderCommand.getOrderId());
        if(orderOptional.isPresent()) {
            Order order = orderOptional.get();
            orderRepository.delete(orderOptional.get());
            return order;
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }
}
