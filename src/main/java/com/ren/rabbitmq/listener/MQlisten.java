package com.ren.rabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author chenjunren 2023/12/21 14:17
 */
@Component
@Slf4j
public class MQlisten {
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String msg) {
        System.out.println("收到simple.queue的消息：" + msg);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQ1(String msg) throws InterruptedException {
        System.out.println("消费者1收到work.queue的消息：" + msg);
        Thread.sleep(20);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQ2(String msg) throws InterruptedException {
        System.err.println("消费者2收到work.queue的消息：" + msg);
        Thread.sleep(200);
    }

//    @RabbitListener(queues = "direct.queue1")
//    public void listenDirectQ1(String msg) throws InterruptedException {
//        System.out.println("消费者1收到direct.queue的消息：" + msg);
//        Thread.sleep(20);
//    }
//
//    @RabbitListener(queues = "direct.queue2")
//    public void topicQ1(String msg) throws InterruptedException {
//        System.err.println("消费者1收到direct.queue的消息：" + msg);
//        Thread.sleep(200);
//    }

    @RabbitListener(queues = "topic.queue1")
    public void topicQ2(String msg) throws InterruptedException {
        System.out.println("消费着1收到topic.queue的消息：" + msg);

    }

    @RabbitListener(queues = "topic.queue2")
    public void listenDirectQ2(String msg) throws InterruptedException {
        System.err.println("消费者2收到topic.queue的消息：" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue6", durable = "true"),
            exchange = @Exchange(name = "c.fanout6", type = ExchangeTypes.FANOUT),
            key = {"red", "blue"}
    ))
    public void listenFanoutQ6(String msg) throws InterruptedException {
        System.err.println("消费者2收到topic.queue的消息：" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue7", durable = "true"),
            exchange = @Exchange(name = "c.fanout7", type = ExchangeTypes.FANOUT),
            key = {"red", "yellow"}
    ))
    public void listenFanoutQ7(String msg) throws InterruptedException {
        System.err.println("消费者2收到topic.queue的消息：" + msg);
    }


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

