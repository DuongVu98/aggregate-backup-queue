package com.epam.aggregatebackupqueue.commands;

import com.epam.aggregatebackupqueue.annotations.TargetIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateOrderCommand {

    @TargetIdentifier
    private final String orderId;

    private final String customerId;
}
