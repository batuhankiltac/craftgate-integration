package com.batuhankiltac.paymentservice.converter;

import com.batuhankiltac.paymentservice.domain.Payment;
import com.batuhankiltac.paymentservice.model.dto.PaymentDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {

    public Payment convert(PaymentDto paymentDto) {
        return Payment.builder()
                .paymentId(paymentDto.getPaymentId())
                .status(paymentDto.getStatus())
                .cardNumber(paymentDto.getCardNumber())
                .amount(paymentDto.getAmount())
                .date(paymentDto.getDate())
                .cardFamily(paymentDto.getCardFamily())
                .installmentNo(paymentDto.getInstallmentNo())
                .installmentCount(paymentDto.getInstallmentCount())
                .errorMessage(paymentDto.getErrorMessage())
                .errorGroup(paymentDto.getErrorGroup())
                .build();
    }
}
