package com.batuhankiltac.craftgateintegrationservice.converter;

import com.batuhankiltac.craftgateintegrationservice.model.BasketItem;
import io.craftgate.request.dto.PaymentItem;
import io.craftgate.response.dto.PaymentTransaction;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemConverter {

    private PaymentItem convert(BasketItem basketItem) {
        return PaymentItem.builder()
                .price(basketItem.getPrice())
                .build();
    }

    public List<PaymentItem> convert(List<BasketItem> basketItems) {
        return basketItems.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public BasketItem convert(PaymentTransaction paymentTransaction) {
        return BasketItem.builder()
                .itemId(String.valueOf(paymentTransaction.getId()))
                .price(paymentTransaction.getPrice())
                .paidPrice(paymentTransaction.getPaidPrice())
                .basketItemTransactionId(String.valueOf(paymentTransaction.getId()))
                .build();
    }
}
