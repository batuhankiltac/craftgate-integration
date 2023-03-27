package com.batuhankiltac.craftgateintegrationservice.service;

import com.batuhankiltac.craftgateintegrationservice.converter.installment.InstallmentConverter;
import com.batuhankiltac.craftgateintegrationservice.model.request.InstallmentRequest;
import io.craftgate.adapter.InstallmentAdapter;
import io.craftgate.request.SearchInstallmentsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InstallmentServiceTest {

    @InjectMocks
    private InstallmentService installmentService;

    @Mock
    private InstallmentConverter installmentConverter;

    @Mock
    private InstallmentAdapter installmentAdapter;

    @Test
    public void it_should_get_installment_list() {
        // Given
        InstallmentRequest installmentRequest = InstallmentRequest.builder()
                .binNumber("12345678")
                .price(BigDecimal.TEN)
                .build();
        SearchInstallmentsRequest searchInstallmentsRequest = SearchInstallmentsRequest.builder()
                .binNumber(installmentRequest.getBinNumber())
                .price(installmentRequest.getPrice())
                .build();
        when(installmentConverter.convert(installmentRequest)).thenReturn(searchInstallmentsRequest);

        // When
        installmentService.getInstallmentList(installmentRequest);

        // Then
        verify(installmentConverter).convert(installmentRequest);
    }
}
