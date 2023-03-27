package com.batuhankiltac.craftgateintegrationservice.converter.installment;

import com.batuhankiltac.craftgateintegrationservice.model.enums.CardAssociation;
import com.batuhankiltac.craftgateintegrationservice.model.enums.CardType;
import com.batuhankiltac.craftgateintegrationservice.model.request.InstallmentRequest;
import com.batuhankiltac.craftgateintegrationservice.model.response.InstallmentResponse;
import io.craftgate.request.SearchInstallmentsRequest;
import io.craftgate.response.dto.Installment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InstallmentConverter {
    private final InstallmentPriceConvert installmentPriceConvert;

    public SearchInstallmentsRequest convert(InstallmentRequest installmentRequest) {
        return SearchInstallmentsRequest.builder()
                .binNumber(installmentRequest.getBinNumber())
                .price(installmentRequest.getPrice())
                .build();
    }

    private InstallmentResponse convert(Installment installment) {
        return InstallmentResponse.builder()
                .bank(installment.getBankCode().toString())
                .cardAssociation(CardAssociation.valueOf(installment.getCardAssociation().name()))
                .cardBrand(installment.getCardBrand())
                .cardType(CardType.valueOf(installment.getCardType().name()))
                .isForce3ds(installment.getForce3ds())
                .installmentPrices(installmentPriceConvert.convert(installment.getInstallmentPrices()))
                .build();
    }

    public List<InstallmentResponse> convert(List<Installment> installments) {
        return installments.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
