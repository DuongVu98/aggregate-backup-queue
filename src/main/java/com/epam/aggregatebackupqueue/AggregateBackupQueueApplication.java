package com.epam.aggregatebackupqueue;

import com.epam.aggregatebackupqueue.aggregate.Item;
import com.epam.aggregatebackupqueue.aggregate.Order;
import com.epam.aggregatebackupqueue.aggregate.ValueObjectId;
import com.epam.aggregatebackupqueue.repositories.ItemRepository;
import com.epam.aggregatebackupqueue.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AggregateBackupQueueApplication implements CommandLineRunner {

	private final OrderRepository orderRepository;
	private final ItemRepository itemRepository;

	@Autowired
	public AggregateBackupQueueApplication(OrderRepository orderRepository, ItemRepository itemRepository) {
		this.orderRepository = orderRepository;
		this.itemRepository = itemRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AggregateBackupQueueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		orderRepository.save(Order.builder()
				.id(new ValueObjectId("order-1"))
				.customerId("customer-1")
				.build()
		);
		orderRepository.save(Order.builder()
				.id(new ValueObjectId("order-2"))
				.customerId("customer-2")
				.build()
		);
		orderRepository.save(Order.builder()
				.id(new ValueObjectId("order-3"))
				.customerId("customer-3")
				.build()
		);
		orderRepository.save(Order.builder()
				.id(new ValueObjectId("order-4"))
				.customerId("customer-4")
				.build()
		);
		orderRepository.save(Order.builder()
				.id(new ValueObjectId("order-5"))
				.customerId("customer-5")
				.build()
		);

		Optional<Order> order3 = orderRepository.findById(new ValueObjectId("order-3"));
		itemRepository.saveAll(List.of(
				Item.builder()
						.id(new ValueObjectId("item-1"))
						.name("something")
						.order(order3.get())
						.build(),
				Item.builder()
						.id(new ValueObjectId("item-2"))
						.name("something")
						.order(order3.get())
						.build(),
				Item.builder()
						.id(new ValueObjectId("item-3"))
						.name("something")
						.order(order3.get())
						.build()
				)
		);
	}
}
