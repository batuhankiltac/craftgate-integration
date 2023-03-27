package com.batuhankiltac.paymentservice.service;

import com.batuhankiltac.paymentservice.client.CraftgateApiClient;
import com.batuhankiltac.paymentservice.converter.PaymentConverter;
import com.batuhankiltac.paymentservice.model.dto.PaymentDto;
import com.batuhankiltac.paymentservice.model.request.CreditCardRequest;
import com.batuhankiltac.paymentservice.model.request.DeleteCardRequest;
import com.batuhankiltac.paymentservice.model.request.PaymentRequest;
import com.batuhankiltac.paymentservice.model.request.RefundRequest;
import com.batuhankiltac.paymentservice.model.response.InstallmentResponse;
import com.batuhankiltac.paymentservice.model.response.PaymentResponse;
import com.batuhankiltac.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentConverter paymentConverter;
    private final CraftgateApiClient craftgateApiClient;

    public PaymentResponse pay(PaymentRequest paymentRequest) {
        return craftgateApiClient.pay(paymentRequest);
    }

    public List<InstallmentResponse> getInstallmentList(String binNumber, BigDecimal price) {
        return craftgateApiClient.getInstallmentList(binNumber, price);
    }

    public void refund(RefundRequest refundRequest) {
        craftgateApiClient.refund(refundRequest);
    }

    public void refundTransaction(RefundRequest refundRequest) {
        craftgateApiClient.refundTransaction(refundRequest);
    }

    public void addCreditCard(CreditCardRequest creditCardRequest) {
        craftgateApiClient.addCreditCard(creditCardRequest);
    }

    public void deleteCreditCard(DeleteCardRequest deleteCardRequest) {
        craftgateApiClient.deleteCreditCard(deleteCardRequest);
    }

    public void save(PaymentDto paymentDto) {
        paymentRepository.save(paymentConverter.convert(paymentDto));
    }
}
