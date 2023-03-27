package com.batuhankiltac.craftgateintegrationservice.converter.payment;

import com.batuhankiltac.craftgateintegrationservice.converter.ItemConverter;
import com.batuhankiltac.craftgateintegrationservice.converter.creditcard.CreditCardConverter;
import com.batuhankiltac.craftgateintegrationservice.model.dto.PaymentDto;
import com.batuhankiltac.craftgateintegrationservice.model.enums.CardAssociation;
import com.batuhankiltac.craftgateintegrationservice.model.enums.CardType;
import com.batuhankiltac.craftgateintegrationservice.model.request.PaymentRequest;
import com.batuhankiltac.craftgateintegrationservice.model.response.PaymentResponse;
import io.craftgate.model.Currency;
import io.craftgate.request.CreatePaymentRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PaymentConverter {
    private final CreditCardConverter creditCardConverter;
    private final ItemConverter itemConverter;
    private static final String DASH = "-";

    public CreatePaymentRequest convert(PaymentRequest paymentRequest) {
        return CreatePaymentRequest.builder()
                .price(paymentRequest.getTotalPrice())
                .paidPrice(paymentRequest.getPaidPrice())
                .walletPrice(BigDecimal.ZERO)
                .installment(paymentRequest.getInstallmentCount())
                .card(creditCardConverter.convert(paymentRequest.getCardDetail()))
                .items(itemConverter.convert(paymentRequest.getBasketItems()))
                .paymentChannel(paymentRequest.getPaymentChannel().name())
                .currency(Currency.TRY)
                .build();
    }

    public PaymentResponse convert(io.craftgate.response.PaymentResponse paymentResponse) {
        return PaymentResponse.builder()
                .createdDate(paymentResponse.getCreatedDate())
                .providerPaymentId(String.valueOf(paymentResponse.getId()))
                .binNumber(paymentResponse.getBinNumber())
                .cardAssociation(CardAssociation.valueOf(paymentResponse.getCardAssociation().name()))
                .cardType(CardType.valueOf(paymentResponse.getCardType().name()))
                .cardFamily(paymentResponse.getCardBrand())
                .basketItems(paymentResponse.getPaymentTransactions()
                                      .stream()
                                      .map(itemConverter::convert)
                                      .collect(Collectors.toList()))
                .status(paymentResponse.getPaymentStatus().name())
                .errorMessage(Objects.nonNull(paymentResponse.getPaymentError()) ?
                                      paymentResponse.getPaymentError().getErrorDescription() : DASH)
                .errorGroup(Objects.nonNull(paymentResponse.getPaymentError()) ?
                                    paymentResponse.getPaymentError().getErrorGroup() : DASH)
                .build();
    }

    public PaymentDto convert(PaymentRequest paymentRequest, io.craftgate.response.PaymentResponse paymentResponse) {
        return PaymentDto.builder()
                .paymentId(paymentRequest.getPaymentId())
                .status(paymentResponse.getPaymentStatus().name())
                .cardNumber(paymentRequest.getCardDetail().getMaskedCardNumber())
                .amount(paymentRequest.getPaidPrice())
                .date(paymentResponse.getCreatedDate())
                .cardFamily(paymentResponse.getCardBrand())
                .installmentNo(paymentRequest.getInstallmentNo())
                .installmentCount(paymentRequest.getInstallmentCount())
                .errorMessage(Objects.nonNull(paymentResponse.getPaymentError()) ?
                                      paymentResponse.getPaymentError().getErrorDescription() : DASH)
                .errorGroup(Objects.nonNull(paymentResponse.getPaymentError()) ?
                                    paymentResponse.getPaymentError().getErrorGroup() : DASH)
                .build();
    }
}
