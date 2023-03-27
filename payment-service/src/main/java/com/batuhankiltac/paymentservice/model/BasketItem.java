package com.batuhankiltac.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketItem {
    private String itemId;
    private String paymentItemTransactionId;
    private BigDecimal price;
    private BigDecimal paidPrice;
}
