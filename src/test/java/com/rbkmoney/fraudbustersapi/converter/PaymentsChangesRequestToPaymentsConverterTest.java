package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.Payment;
import com.rbkmoney.damsel.fraudbusters.PaymentStatus;
import com.rbkmoney.fraudbustersapi.utils.ApiBeanGenerator;
import com.rbkmoney.swag.fraudbusters.model.PaymentsChangesRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentsChangesRequestToPaymentsConverterTest {

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
        PaymentsChangesRequestToPaymentsConverter converter = new PaymentsChangesRequestToPaymentsConverter(
                cacheToInternalDtoConverter,
                clientInfoToInternalDtoConverter,
                paymentToolToInternalDtoConverter,
                providerInfoToInternalDtoConverter,
                referenceInfoToInternalDtoConverter,
                errorToInternalDtoConverter
        );

        List<Payment> payments =
                converter.convert(new PaymentsChangesRequest()
                        .paymentsChanges(
                                List.of(ApiBeanGenerator.initPaymentChange()))
                );

        assertNotNull(payments);
        assertFalse(payments.isEmpty());

        Payment payment = payments.get(0);
        assertNotNull(payment);
        assertEquals(ApiBeanGenerator.ID, payment.getId());
        assertNotNull(payment.getEventTime());
        assertEquals(PaymentStatus.captured, payment.getStatus());
        assertEquals(ApiBeanGenerator.MOBILE, payment.isMobile());
        assertEquals(ApiBeanGenerator.RECURRENT, payment.isRecurrent());
    }
}
