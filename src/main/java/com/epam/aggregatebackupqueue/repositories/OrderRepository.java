package com.epam.aggregatebackupqueue.repositories;

import com.epam.aggregatebackupqueue.aggregate.Order;
import com.epam.aggregatebackupqueue.aggregate.ValueObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, ValueObjectId>, CrudRepository<Order, ValueObjectId> {
}
