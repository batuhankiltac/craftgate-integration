package com.batuhankiltac.craftgateintegrationservice.converter.refund;

import com.batuhankiltac.craftgateintegrationservice.model.request.RefundRequest;
import io.craftgate.model.RefundDestinationType;
import io.craftgate.request.RefundPaymentRequest;
import io.craftgate.request.RefundPaymentTransactionRequest;
import org.springframework.stereotype.Component;

@Component
public class RefundConverter {

    public RefundPaymentRequest convert(RefundRequest refundRequest) {
        return RefundPaymentRequest.builder()
                .paymentId(Long.valueOf(refundRequest.getPaymentId()))
                .refundDestinationType(RefundDestinationType.PROVIDER)
                .build();
    }

    public RefundPaymentTransactionRequest convertTo(RefundRequest refundRequest) {
        return RefundPaymentTransactionRequest.builder()
                .paymentTransactionId(Long.valueOf(refundRequest.getPaymentTransactionId()))
                .refundPrice(refundRequest.getPrice())
                .refundDestinationType(RefundDestinationType.PROVIDER)
                .build();
    }
}
