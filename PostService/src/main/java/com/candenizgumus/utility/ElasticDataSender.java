package com.candenizgumus.utility;


import com.candenizgumus.services.PostService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ElasticDataSender
{
    private final PostService postService;
    private final RabbitTemplate rabbitTemplate;


    //@PostConstruct
    public void send(){

        postService.findAll().forEach(post -> {
            rabbitTemplate.convertAndSend("exchange.direct","Routing.savepost",post);
        });


    }

}
