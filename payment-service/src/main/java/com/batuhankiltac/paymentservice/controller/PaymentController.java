package com.batuhankiltac.paymentservice.controller;

import com.batuhankiltac.paymentservice.model.request.CreditCardRequest;
import com.batuhankiltac.paymentservice.model.request.DeleteCardRequest;
import com.batuhankiltac.paymentservice.model.request.InstallmentRequest;
import com.batuhankiltac.paymentservice.model.request.PaymentRequest;
import com.batuhankiltac.paymentservice.model.request.RefundRequest;
import com.batuhankiltac.paymentservice.model.response.InstallmentResponse;
import com.batuhankiltac.paymentservice.model.response.PaymentResponse;
import com.batuhankiltac.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(value = "/pay")
    public PaymentResponse pay(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.pay(paymentRequest);
    }

    @GetMapping(value = "/installments")
    public List<InstallmentResponse> getInstallmentList(InstallmentRequest installmentRequest) {
        return paymentService.getInstallmentList(installmentRequest.getBinNumber(), installmentRequest.getPrice());
    }

    @PostMapping(value = "/refunds")
    public void refund(@RequestBody RefundRequest refundRequest) {
        paymentService.refund(refundRequest);
    }

    @PostMapping(value = "/refunds/transactions")
    public void refundTransaction(@RequestBody RefundRequest refundRequest) {
        paymentService.refundTransaction(refundRequest);
    }

    @PostMapping(value = "/credit-cards")
    public void addCreditCard(@RequestBody CreditCardRequest creditCardRequest) {
        paymentService.addCreditCard(creditCardRequest);
    }

    @DeleteMapping(value = "/credit-cards")
    public void deleteCreditCard(@RequestBody DeleteCardRequest deleteCardRequest) {
        paymentService.deleteCreditCard(deleteCardRequest);
    }
}
