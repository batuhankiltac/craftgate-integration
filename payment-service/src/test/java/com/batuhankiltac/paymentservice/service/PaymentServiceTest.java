package com.batuhankiltac.paymentservice.service;

import com.batuhankiltac.paymentservice.client.CraftgateApiClient;
import com.batuhankiltac.paymentservice.converter.PaymentConverter;
import com.batuhankiltac.paymentservice.domain.Payment;
import com.batuhankiltac.paymentservice.model.dto.PaymentDto;
import com.batuhankiltac.paymentservice.model.request.CreditCardRequest;
import com.batuhankiltac.paymentservice.model.request.DeleteCardRequest;
import com.batuhankiltac.paymentservice.model.request.PaymentRequest;
import com.batuhankiltac.paymentservice.model.request.RefundRequest;
import com.batuhankiltac.paymentservice.model.response.InstallmentResponse;
import com.batuhankiltac.paymentservice.model.response.PaymentResponse;
import com.batuhankiltac.paymentservice.repository.PaymentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentConverter paymentConverter;

    @Mock
    private CraftgateApiClient craftgateApiClient;

    @Test
    public void it_should_pay() {
        // Given
        PaymentRequest paymentRequest = PaymentRequest.builder().build();
        PaymentResponse paymentResponse = PaymentResponse.builder().build();
        when(craftgateApiClient.pay(paymentRequest)).thenReturn(paymentResponse);

        // When
        paymentService.pay(paymentRequest);

        // Then
        verify(craftgateApiClient).pay(paymentRequest);
    }

    @Test
    public void it_should_get_installments() {
        // Given
        String binNumber = "12345678";
        BigDecimal price = BigDecimal.TEN;
        InstallmentResponse installmentResponse = InstallmentResponse.builder().build();
        List<InstallmentResponse> installmentResponseList = Collections.singletonList(installmentResponse);
        when(craftgateApiClient.getInstallmentList(binNumber, price)).thenReturn(installmentResponseList);

        // When
        paymentService.getInstallmentList(binNumber, price);

        // Then
        verify(craftgateApiClient).getInstallmentList(binNumber, price);
    }

    @Test
    public void it_should_refund() {
        // Given
        RefundRequest refundRequest = RefundRequest.builder().build();

        // When
        paymentService.refund(refundRequest);

        // Then
        verify(craftgateApiClient).refund(refundRequest);
    }

    @Test
    public void it_should_refund_transaction() {
        // Given
        RefundRequest refundRequest = RefundRequest.builder().build();

        // When
        paymentService.refundTransaction(refundRequest);

        // Then
        verify(craftgateApiClient).refundTransaction(refundRequest);
    }

    @Test
    public void it_should_add_credit_card() {
        // Given
        CreditCardRequest creditCardRequest = CreditCardRequest.builder().build();

        // When
        paymentService.addCreditCard(creditCardRequest);

        // Then
        verify(craftgateApiClient).addCreditCard(creditCardRequest);
    }

    @Test
    public void it_should_delete_credit_card() {
        // Given
        DeleteCardRequest deleteCardRequest = DeleteCardRequest.builder().build();

        // When
        paymentService.deleteCreditCard(deleteCardRequest);

        // Then
        verify(craftgateApiClient).deleteCreditCard(deleteCardRequest);
    }

    @Test
    public void it_should_save() {
        // Given
        PaymentDto paymentDto = PaymentDto.builder().build();
        Payment payment = Payment.builder().build();
        when(paymentConverter.convert(paymentDto)).thenReturn(payment);

        // When
        paymentService.save(paymentDto);

        // Then
        verify(paymentConverter).convert(paymentDto);
        verify(paymentRepository).save(payment);
    }
}
