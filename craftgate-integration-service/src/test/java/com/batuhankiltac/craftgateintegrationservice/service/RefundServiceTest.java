package com.batuhankiltac.craftgateintegrationservice.service;

import com.batuhankiltac.craftgateintegrationservice.converter.refund.RefundConverter;
import com.batuhankiltac.craftgateintegrationservice.model.request.RefundRequest;
import io.craftgate.adapter.PaymentAdapter;
import io.craftgate.model.RefundDestinationType;
import io.craftgate.request.RefundPaymentRequest;
import io.craftgate.request.RefundPaymentTransactionRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RefundServiceTest {

    @InjectMocks
    private RefundService refundService;

    @Mock
    private RefundConverter refundConverter;

    @Mock
    private PaymentAdapter paymentAdapter;

    @Test
    public void it_should_refund() {
        // Given
        RefundRequest refundRequest = RefundRequest.builder()
                .paymentId(23424)
                .paymentTransactionId(143242432)
                .price(BigDecimal.TEN)
                .build();
        RefundPaymentRequest refundPaymentRequest = RefundPaymentRequest.builder()
                .paymentId(Long.valueOf(refundRequest.getPaymentId()))
                .refundDestinationType(RefundDestinationType.PROVIDER)
                .build();
        when(refundConverter.convert(refundRequest)).thenReturn(refundPaymentRequest);

        // When
        refundService.refund(refundRequest);

        // Then
        verify(refundConverter).convert(refundRequest);
    }

    @Test
    public void it_should_refund_transaction() {
        // Given
        RefundRequest refundRequest = RefundRequest.builder()
                .paymentId(248023)
                .paymentTransactionId(143242432)
                .price(BigDecimal.TEN)
                .build();
        RefundPaymentTransactionRequest refundPaymentTransactionRequest = RefundPaymentTransactionRequest.builder()
                .paymentTransactionId(Long.valueOf(refundRequest.getPaymentTransactionId()))
                .refundPrice(BigDecimal.ONE)
                .refundDestinationType(RefundDestinationType.PROVIDER)
                .build();
        when(refundConverter.convertTo(refundRequest)).thenReturn(refundPaymentTransactionRequest);

        // When
        refundService.refundTransaction(refundRequest);

        // Then
        verify(refundConverter).convertTo(refundRequest);
    }
}
