package com.ren.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjunren 2023/12/21 14:06
 */
@SpringBootTest
public class RabbitSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testSendMsg() {
        String queueName = "simple.queue";
        String msg = "hello,amqp!";
        rabbitTemplate.convertAndSend(queueName, msg);

    }

    @Test
    void testWorkQ() throws InterruptedException {
        String exhangeChange = "c.fanout";
        String msg = "hello,amqp!";
        rabbitTemplate.convertAndSend(exhangeChange, null, msg);
    }

    @Test
    void testDirSend() throws InterruptedException {
        String exhangeChange = "c.direct";
        String msg = "hello,amqp!";
        rabbitTemplate.convertAndSend(exhangeChange, "yellow", msg);
    }

    @Test
    void testTopSend() throws Exception {
        String exhangeChange = "c.topic";
        String msg = "今早八点起床";
        rabbitTemplate.convertAndSend(exhangeChange, "china.nothing", msg);
    }

    @Test
    void testSendObj() {
        Map<String,Object> msg = new HashMap<>();
        msg.put("name","jack");
        msg.put("age",21);
        rabbitTemplate.convertAndSend("object.queue",msg);
    }
}
