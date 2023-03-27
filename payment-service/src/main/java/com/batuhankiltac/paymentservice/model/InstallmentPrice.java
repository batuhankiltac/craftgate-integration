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
public class InstallmentPrice {
    private BigDecimal installmentPrice;
    private BigDecimal totalPrice;
    private Integer installmentNumber;
}
