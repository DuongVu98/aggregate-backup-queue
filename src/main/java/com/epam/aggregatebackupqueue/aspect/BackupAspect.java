package com.epam.aggregatebackupqueue.aspect;

import com.epam.aggregatebackupqueue.aggregate.Order;
import com.epam.aggregatebackupqueue.annotations.TargetIdentifier;
import com.epam.aggregatebackupqueue.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Queue;

@Aspect
@Component
@Slf4j(topic = "[BackupAspect]")
public class BackupAspect {

    private final Queue<Order> orderBackupQueue;
    private final OrderRepository orderRepository;

    @Autowired
    public BackupAspect(@Qualifier("backup-queue") Queue<Order> orderBackupQueue, OrderRepository orderRepository) {
        this.orderBackupQueue = orderBackupQueue;
        this.orderRepository = orderRepository;
    }

    @Pointcut("@annotation(com.epam.aggregatebackupqueue.annotations.AggregateBackup)")
    public void aggregateBackupAnnotation() {
    }

    @Pointcut("within(com.epam.aggregatebackupqueue.controller.CommandGateway)")
    public void commandGatewayPointcut(){}

    @Before("aggregateBackupAnnotation() && commandGatewayPointcut()")
    public void doSomething(JoinPoint joinPoint) throws IllegalAccessException {
        Object[] args = joinPoint.getArgs();
        Object targetCommand = args[0];
        log.info("command --> {}", targetCommand.toString());

        Field targetField = null;
        for (Field field : targetCommand.getClass().getDeclaredFields()) {
            if (isContainTargetIdentifierAnnotation(field)) {
                targetField = field;
                break;
            }
        }

        if (targetField != null) {
            String value = (String) targetField.get(targetCommand);
            Optional<Order> orderOptional = orderRepository.findById(value);

            if(orderOptional.isPresent()){
                Order order = orderOptional.get();
                log.info(order.toString());
                orderBackupQueue.add(order);
            }
        }
    }

    public boolean isContainTargetIdentifierAnnotation(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(TargetIdentifier.class)) {
                return true;
            }
        }
        return false;
    }
}
