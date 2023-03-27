package com.batuhankiltac.craftgateintegrationservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundRequest {

    @NotNull(message = "refundRequest.paymentId.notNull")
    private Integer paymentId;

    @NotNull(message = "refundRequest.paymentTransactionId.notNull")
    private Integer paymentTransactionId;

    @NotNull(message = "refundRequest.price.notNull")
    private BigDecimal price;
}
