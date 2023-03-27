package com.batuhankiltac.craftgateintegrationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDetail {
    @NotBlank(message = "cardDetail.cardHolderName.notBlank")
    private String cardHolderName;

    @NotBlank(message = "cardDetail.cardNumber.notBlank")
    @Size(min = 15, max = 16, message = "cardDetail.cardNumber.size")
    private String cardNumber;

    @NotBlank(message = "cardDetail.expireYear.notBlank")
    private String expireYear;

    @NotBlank(message = "cardDetail.expireMonth.notBlank")
    @Size(min = 1, max = 12, message = "cardDetail.expireMonth.size")
    private String expireMonth;

    @NotBlank(message = "cardDetail.cvc.notBlank")
    @Size(min = 3, max = 4, message = "cardDetail.cvc.size")
    private String cvc;
    private String cardUserKey;
    private boolean registerCard;

    public String getMaskedCardNumber() {
        return StringUtils.overlay(this.cardNumber, StringUtils.repeat("*", 6), 6, 12);
    }
}
