package com.batuhankiltac.paymentservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardRequest {
    private String cardHolderName;
    private String cardNumber;
    private String expireYear;
    private String expireMonth;
}
