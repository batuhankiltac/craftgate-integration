package com.batuhankiltac.paymentservice.client;

import com.batuhankiltac.paymentservice.model.request.CreditCardRequest;
import com.batuhankiltac.paymentservice.model.request.DeleteCardRequest;
import com.batuhankiltac.paymentservice.model.request.PaymentRequest;
import com.batuhankiltac.paymentservice.model.request.RefundRequest;
import com.batuhankiltac.paymentservice.model.response.InstallmentResponse;
import com.batuhankiltac.paymentservice.model.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "craftgateApiClient", url = "http://localhost:8081")
public interface CraftgateApiClient {

    @PostMapping(value = "/craftgate/payments")
    PaymentResponse pay(@RequestBody PaymentRequest paymentRequest);

    @GetMapping(value = "/craftgate/installments")
    List<InstallmentResponse> getInstallmentList(@RequestParam String binNumber, @RequestParam BigDecimal price);

    @PostMapping(value = "/craftgate/refunds")
    void refund(@RequestBody RefundRequest refundRequest);

    @PostMapping(value = "/craftgate/refunds/transactions")
    void refundTransaction(@RequestBody RefundRequest refundRequest);

    @PostMapping(value = "/craftgate/credit-cards")
    void addCreditCard(@RequestBody CreditCardRequest creditCardRequest);

    @DeleteMapping(value = "/craftgate/credit-cards")
    void deleteCreditCard(@RequestBody DeleteCardRequest deleteCardRequest);
}
