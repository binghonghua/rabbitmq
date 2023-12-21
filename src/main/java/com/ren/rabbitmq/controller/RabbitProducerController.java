package com.ren.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chenjunren 2023/12/21 11:19
 */
@RestController
public class RabbitProducerController {
    @Resource
    private AmqpTemplate rabbitTemplate;
    /**
     * 生产者向hello消息队列发送消息
     * @param name
     * @return
     */
    @RequestMapping("/produce")
    public String produce(@RequestParam String name) {
        rabbitTemplate.convertAndSend("hello", "send msg is " + name);
        return "消息发送成功";
    }
}
