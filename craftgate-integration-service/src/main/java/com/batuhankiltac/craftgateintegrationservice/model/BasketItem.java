package com.batuhankiltac.craftgateintegrationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketItem {
    @NotBlank(message = "basketItem.itemId.notBlank")
    private String itemId;

    @NotBlank(message = "basketItem.basketItemTransactionId.notBlank")
    private String basketItemTransactionId;

    @NotNull(message = "basketItem.price.notNull")
    private BigDecimal price;

    @NotNull(message = "basketItem.paidPrice.notNull")
    private BigDecimal paidPrice;
}
