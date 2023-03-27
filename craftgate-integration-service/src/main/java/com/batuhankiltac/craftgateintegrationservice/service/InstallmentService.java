package com.batuhankiltac.craftgateintegrationservice.service;

import com.batuhankiltac.craftgateintegrationservice.configuration.craftgate.CraftgateConfiguration;
import com.batuhankiltac.craftgateintegrationservice.converter.installment.InstallmentConverter;
import com.batuhankiltac.craftgateintegrationservice.model.request.InstallmentRequest;
import com.batuhankiltac.craftgateintegrationservice.model.response.InstallmentResponse;
import io.craftgate.adapter.InstallmentAdapter;
import io.craftgate.request.SearchInstallmentsRequest;
import io.craftgate.response.InstallmentListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InstallmentService {
    private final InstallmentConverter installmentConverter;
    private final InstallmentAdapter installmentAdapter = new InstallmentAdapter(CraftgateConfiguration.getOptions());

    public List<InstallmentResponse> getInstallmentList(InstallmentRequest installmentRequest) {
        SearchInstallmentsRequest searchInstallmentsRequest = installmentConverter.convert(installmentRequest);
        log.info("searchInstallmentsRequest: {}", searchInstallmentsRequest);

        InstallmentListResponse installmentListResponse = installmentAdapter.searchInstallments(searchInstallmentsRequest);
        log.info("installmentListResponse: {}", installmentListResponse);

        return installmentConverter.convert(installmentListResponse.getItems());
    }
}
