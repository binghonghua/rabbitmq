package com.ren.rabbitmq.listener.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenjunren 2023/12/21 15:57
 */
@Configuration
public class FanoutConfiguration {

    /*交换机*/
    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("c.fanout2").build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("fanout.queue3").build();
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

}
