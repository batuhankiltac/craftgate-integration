package com.batuhankiltac.paymentservice.consumer;

import com.batuhankiltac.paymentservice.model.dto.PaymentDto;
import com.batuhankiltac.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentConsumer {
    private final PaymentService paymentService;

    @RabbitListener(queues = "payment.queue")
    public void consume(PaymentDto paymentDto) {
        log.info("consumer paymentDto -> {} ", paymentDto);
        paymentService.save(paymentDto);
    }
}
