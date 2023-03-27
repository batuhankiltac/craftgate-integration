package com.batuhankiltac.craftgateintegrationservice.controller;

import com.batuhankiltac.craftgateintegrationservice.model.request.CreditCardRequest;
import com.batuhankiltac.craftgateintegrationservice.model.request.DeleteCardRequest;
import com.batuhankiltac.craftgateintegrationservice.model.request.InstallmentRequest;
import com.batuhankiltac.craftgateintegrationservice.model.request.PaymentRequest;
import com.batuhankiltac.craftgateintegrationservice.model.request.RefundRequest;
import com.batuhankiltac.craftgateintegrationservice.model.response.InstallmentResponse;
import com.batuhankiltac.craftgateintegrationservice.model.response.PaymentResponse;
import com.batuhankiltac.craftgateintegrationservice.service.CardService;
import com.batuhankiltac.craftgateintegrationservice.service.InstallmentService;
import com.batuhankiltac.craftgateintegrationservice.service.PaymentService;
import com.batuhankiltac.craftgateintegrationservice.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/craftgate")
public class CraftgateController {
    private final PaymentService paymentService;
    private final InstallmentService installmentService;
    private final RefundService refundService;
    private final CardService cardService;

    @PostMapping(value = "/payments")
    public PaymentResponse pay(@RequestBody @Valid PaymentRequest paymentRequest) {
        return paymentService.pay(paymentRequest);
    }

    @GetMapping(value = "/installments")
    public List<InstallmentResponse> getInstallmentList(@Valid InstallmentRequest installmentRequest) {
        return installmentService.getInstallmentList(installmentRequest);
    }

    @PostMapping(value = "/refunds")
    public void refund(@RequestBody @Valid RefundRequest refundRequest) {
        refundService.refund(refundRequest);
    }

    @PostMapping(value = "/refunds/transactions")
    public void refundTransaction(@RequestBody @Valid RefundRequest refundRequest) {
        refundService.refundTransaction(refundRequest);
    }

    @PostMapping(value = "/credit-cards")
    public void addCreditCard(@RequestBody @Valid CreditCardRequest creditCardRequest) {
        cardService.addCreditCard(creditCardRequest);
    }

    @DeleteMapping(value = "/credit-cards")
    public void deleteCreditCard(@RequestBody @Valid DeleteCardRequest deleteCardRequest) {
        cardService.deleteCreditCard(deleteCardRequest);
    }
}
