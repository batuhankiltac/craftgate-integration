package com.batuhankiltac.craftgateintegrationservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstallmentRequest {
    @Size(min = 8, max = 8, message = "installmentRequest.binNumber.size")
    private String binNumber;

    @NotNull(message = "installmentRequest.price.notNull")
    private BigDecimal price;
}
