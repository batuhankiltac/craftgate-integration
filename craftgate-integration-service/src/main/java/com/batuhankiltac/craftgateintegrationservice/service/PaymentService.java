package com.batuhankiltac.craftgateintegrationservice.service;

import com.batuhankiltac.craftgateintegrationservice.configuration.craftgate.CraftgateConfiguration;
import com.batuhankiltac.craftgateintegrationservice.converter.payment.PaymentConverter;
import com.batuhankiltac.craftgateintegrationservice.helper.CraftgateValidator;
import com.batuhankiltac.craftgateintegrationservice.model.dto.PaymentDto;
import com.batuhankiltac.craftgateintegrationservice.model.request.CreditCardRequest;
import com.batuhankiltac.craftgateintegrationservice.model.request.PaymentRequest;
import com.batuhankiltac.craftgateintegrationservice.model.response.PaymentResponse;
import com.batuhankiltac.craftgateintegrationservice.publisher.PaymentPublisher;
import io.craftgate.adapter.PaymentAdapter;
import io.craftgate.request.CreatePaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentConverter paymentConverter;
    private final PaymentPublisher paymentPublisher;
    private final CardService cardService;
    private final PaymentAdapter paymentAdapter = new PaymentAdapter(CraftgateConfiguration.getOptions());

    public PaymentResponse pay(PaymentRequest paymentRequest) {
        CreatePaymentRequest createPaymentRequest = paymentConverter.convert(paymentRequest);
        log.info("createPaymentRequest: {}", createPaymentRequest);

        io.craftgate.response.PaymentResponse paymentResponse = paymentAdapter.createPayment(createPaymentRequest);
        log.info("paymentResponse: {}", paymentResponse);

        CraftgateValidator.assertIfCraftgatePaymentStatusIsSuccess(paymentResponse);

        cardService.addCreditCard(CreditCardRequest.builder()
                                .cardHolderName(paymentRequest.getCardDetail().getCardHolderName())
                                .cardNumber(paymentRequest.getCardDetail().getCardNumber())
                                .expireMonth(paymentRequest.getCardDetail().getExpireMonth())
                                .expireYear(paymentRequest.getCardDetail().getExpireYear())
                                .build());

        PaymentDto paymentDto = paymentConverter.convert(paymentRequest, paymentResponse);

        paymentPublisher.publish(paymentDto);

        return paymentConverter.convert(paymentResponse);
    }
}
