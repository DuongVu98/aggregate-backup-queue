package com.epam.aggregatebackupqueue.controller;

import com.epam.aggregatebackupqueue.aggregate.Order;
import com.epam.aggregatebackupqueue.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@RestController
@RequestMapping(value = "/external")
@Slf4j(topic = "[ExternalController]")
public class ExternalController {

    private final Queue<Order> orderQueue;
    private final OrderRepository orderRepository;

    @Autowired
    public ExternalController(@Qualifier("backup-queue") Queue<Order> orderQueue, OrderRepository orderRepository) {
        this.orderQueue = orderQueue;
        this.orderRepository = orderRepository;
    }

    @GetMapping(value = "/success")
    public void success() {
        Order order = orderQueue.peek();
        if (order != null) {
            log.info(order.toString());
        }
    }

    @GetMapping(value = "/failure")
    public void failure() {
        Order order = orderQueue.peek();
        if (order != null) {
            log.info(order.toString());
            this.orderRepository.save(order);
        }
    }
}
