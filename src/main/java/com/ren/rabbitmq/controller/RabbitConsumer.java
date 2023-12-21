package com.ren.rabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chenjunren 2023/12/21 11:22
 */
@Component
@RabbitListener(queues = "hello")
public class RabbitConsumer {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("------------" + msg);
    }
}
