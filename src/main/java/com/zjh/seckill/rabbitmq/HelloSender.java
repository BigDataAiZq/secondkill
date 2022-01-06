package com.zjh.seckill.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", "hello rabbit!");
    }

}