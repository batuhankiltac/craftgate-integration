package com.batuhankiltac.craftgateintegrationservice.converter.installment;

import com.batuhankiltac.craftgateintegrationservice.model.InstallmentPrice;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstallmentPriceConvert {

    private InstallmentPrice convert(io.craftgate.response.dto.InstallmentPrice installmentPrice) {
        return InstallmentPrice.builder()
                .installmentPrice(installmentPrice.getInstallmentPrice())
                .totalPrice(installmentPrice.getTotalPrice())
                .installmentNumber(installmentPrice.getInstallmentNumber())
                .build();
    }

    public List<InstallmentPrice> convert(List<io.craftgate.response.dto.InstallmentPrice> installmentPrices) {
        return installmentPrices.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
