package com.epam.aggregatebackupqueue.controller;

import com.epam.aggregatebackupqueue.aggregate.Order;
import com.epam.aggregatebackupqueue.annotations.AggregateBackup;
import com.epam.aggregatebackupqueue.commands.CreateOrderCommand;
import com.epam.aggregatebackupqueue.commands.DeleteOrderCommand;
import com.epam.aggregatebackupqueue.executor.AbstractCommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandGateway {

    private final AbstractCommandExecutor<CreateOrderCommand, Order> createOrderCommandExecutor;
    private final AbstractCommandExecutor<DeleteOrderCommand, Order> deleteOrderCommandExecutor;

    @Autowired
    public CommandGateway(AbstractCommandExecutor<CreateOrderCommand, Order> createOrderCommandExecutor, AbstractCommandExecutor<DeleteOrderCommand, Order> deleteOrderCommandExecutor) {
        this.createOrderCommandExecutor = createOrderCommandExecutor;
        this.deleteOrderCommandExecutor = deleteOrderCommandExecutor;
    }

    public void apply(CreateOrderCommand command){
        createOrderCommandExecutor.execute(command);
    }

    @AggregateBackup
    public void apply(DeleteOrderCommand command){
        deleteOrderCommandExecutor.execute(command);
    }
}
