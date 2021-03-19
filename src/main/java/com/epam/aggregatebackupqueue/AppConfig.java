package com.epam.aggregatebackupqueue;

import com.epam.aggregatebackupqueue.aggregate.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.PriorityQueue;
import java.util.Queue;

@Configuration
public class AppConfig {

    @Bean("backup-queue")
    public Queue<Order> orderQueue() {
        return new PriorityQueue<>();
    }
}
