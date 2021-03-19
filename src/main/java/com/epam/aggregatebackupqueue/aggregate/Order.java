package com.epam.aggregatebackupqueue.aggregate;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_orders")
public class Order implements Comparable<Order>{

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private ValueObjectId id;

    @Column(name = "customer")
    private String customerId;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    @Override
    public int compareTo(Order order) {
        return this.id.getValue().compareTo(order.getId().getValue());
    }
}
