package com.batuhankiltac.paymentservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundRequest {
    private Integer paymentId;
    private Integer paymentTransactionId;
    private BigDecimal price;
}
