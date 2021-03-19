package com.epam.aggregatebackupqueue.commands;

import com.epam.aggregatebackupqueue.annotations.TargetIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteOrderCommand {

    @TargetIdentifier
    public final String orderId;
}
