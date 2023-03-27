package com.batuhankiltac.craftgateintegrationservice.publisher;

import com.batuhankiltac.craftgateintegrationservice.configuration.rabbitmq.RabbitMqConfiguration;
import com.batuhankiltac.craftgateintegrationservice.model.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqConfiguration rabbitMqConfiguration;

    public void publish(PaymentDto paymentDto) {
        rabbitTemplate.convertAndSend(rabbitMqConfiguration.getExchange(), rabbitMqConfiguration.getRoutingkey() , paymentDto);
    }
}
