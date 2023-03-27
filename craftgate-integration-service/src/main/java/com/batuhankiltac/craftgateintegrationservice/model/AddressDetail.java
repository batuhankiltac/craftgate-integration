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
public class AddressDetail {
    @NotBlank(message = "addressDetail.address.notBlank")
    private String address;

    @NotBlank(message = "addressDetail.city.notBlank")
    private String city;

    @NotBlank(message = "addressDetail.country.notBlank")
    private String country;
}
