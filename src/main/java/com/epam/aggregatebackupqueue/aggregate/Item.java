package com.epam.aggregatebackupqueue.aggregate;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class Item {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private ValueObjectId id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
