package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.ProviderInfo;
import com.rbkmoney.fraudbustersapi.utils.ApiBeanGenerator;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProviderInfoToInternalDtoConverterTest {

    @Test
    void convert() throws TException {
        ProviderInfoToInternalDtoConverter converter = new ProviderInfoToInternalDtoConverter();

        ProviderInfo providerInfo = converter.convert(ApiBeanGenerator.initProviderInfo());

        assertNotNull(providerInfo);
        providerInfo.validate();

        assertEquals(ApiBeanGenerator.PROVIDER_ID, providerInfo.getProviderId());
        assertEquals(ApiBeanGenerator.RUS, providerInfo.getCountry());
        assertEquals(ApiBeanGenerator.TERMINAL_ID, providerInfo.getTerminalId());
    }
}
