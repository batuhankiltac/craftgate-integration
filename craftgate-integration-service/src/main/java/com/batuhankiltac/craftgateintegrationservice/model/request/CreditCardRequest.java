package com.batuhankiltac.craftgateintegrationservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardRequest {
    @NotBlank(message = "creditCardRequest.cardHolderName.notBlank")
    private String cardHolderName;

    @NotBlank(message = "creditCardRequest.cardNumber.notBlank")
    @Size(min = 15, max = 16, message = "creditCardRequest.cardNumber.size")
    private String cardNumber;

    @NotBlank(message = "creditCardRequest.expireYear.notBlank")
    private String expireYear;

    @NotBlank(message = "creditCardRequest.expireMonth.notBlank")
    @Size(min = 1, max = 12, message = "creditCardRequestDetail.expireMonth.size")
    private String expireMonth;
}
