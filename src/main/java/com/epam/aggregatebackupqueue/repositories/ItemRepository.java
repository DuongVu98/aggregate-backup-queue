package com.epam.aggregatebackupqueue.repositories;

import com.epam.aggregatebackupqueue.aggregate.Item;
import com.epam.aggregatebackupqueue.aggregate.ValueObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, ValueObjectId>, CrudRepository<Item, ValueObjectId> {
}
