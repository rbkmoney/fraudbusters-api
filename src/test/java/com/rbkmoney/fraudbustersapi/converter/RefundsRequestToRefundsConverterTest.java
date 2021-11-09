package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.Refund;
import com.rbkmoney.damsel.fraudbusters.RefundStatus;
import com.rbkmoney.fraudbustersapi.utils.ApiBeanGenerator;
import com.rbkmoney.swag.fraudbusters.model.RefundsRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RefundsRequestToRefundsConverterTest {

    @Mock
    CacheToInternalDtoConverter cacheToInternalDtoConverter;
    @Mock
    ClientInfoToInternalDtoConverter clientInfoToInternalDtoConverter;
    @Mock
    PaymentToolToInternalDtoConverter paymentToolToInternalDtoConverter;
    @Mock
    ProviderInfoToInternalDtoConverter providerInfoToInternalDtoConverter;
    @Mock
    ReferenceInfoToInternalDtoConverter referenceInfoToInternalDtoConverter;
    @Mock
    ErrorToInternalDtoConverter errorToInternalDtoConverter;

    @Test
    void convert() {
        RefundsRequestToRefundsConverter converter = new RefundsRequestToRefundsConverter(
                cacheToInternalDtoConverter,
                clientInfoToInternalDtoConverter,
                paymentToolToInternalDtoConverter,
                providerInfoToInternalDtoConverter,
                referenceInfoToInternalDtoConverter,
                errorToInternalDtoConverter
        );

        List<Refund> refunds = converter.convert(new RefundsRequest().refunds(
                List.of(ApiBeanGenerator.initRefund())));

        assertNotNull(refunds);
        assertFalse(refunds.isEmpty());

        Refund refund = refunds.get(0);
        assertNotNull(refund);
        assertEquals(ApiBeanGenerator.ID, refund.getId());
        assertNotNull(refund.getEventTime());
        assertEquals(RefundStatus.succeeded, refund.getStatus());
        assertEquals(ApiBeanGenerator.PAYMENT_ID, refund.getPaymentId());
    }
}
