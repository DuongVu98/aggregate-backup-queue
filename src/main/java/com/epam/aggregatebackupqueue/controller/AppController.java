package com.epam.aggregatebackupqueue.controller;

import com.epam.aggregatebackupqueue.commands.CreateOrderCommand;
import com.epam.aggregatebackupqueue.commands.DeleteOrderCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/home")
public class AppController {

    private final CommandGateway commandGateway;

    @Autowired
    public AppController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping(value = "/")
    public String getHome(){
        return "This is demo for aggregate backup solution";
    }

    @PostMapping(value = "/create")
    public void create(){
        commandGateway.apply(new CreateOrderCommand("", "customer-1"));
    }

    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam("orderId") String orderId) {
        commandGateway.apply(new DeleteOrderCommand(orderId));
    }
}
