package com.batuhankiltac.craftgateintegrationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyerDetail {
    @NotBlank(message = "buyerDetail.id.notBlank")
    private String id;

    @NotBlank(message = "buyerDetail.name.notBlank")
    private String name;

    @NotBlank(message = "buyerDetail.surname.notBlank")
    private String surname;

    @NotBlank(message = "buyerDetail.identityNumber.notBlank")
    private String identityNumber;

    @NotBlank(message = "buyerDetail.email.notBlank")
    private String email;
    private String gsmNumber;
    private String registrationAddress;
    private String city;
    private String country;
}
