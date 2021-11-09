package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.ClientInfo;
import com.rbkmoney.fraudbustersapi.utils.ApiBeanGenerator;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientInfoToInternalDtoConverterTest {

    @Test
    void convert() throws TException {
        ClientInfoToInternalDtoConverter clientInfoToInternalDtoConverter = new ClientInfoToInternalDtoConverter();

        ClientInfo clientInfo = clientInfoToInternalDtoConverter.convert(ApiBeanGenerator.initUserInfo());

        assertNotNull(clientInfo);
        clientInfo.validate();

        assertEquals(ApiBeanGenerator.EMAIL, clientInfo.getEmail());
        assertEquals(ApiBeanGenerator.FINGERPRONT, clientInfo.getFingerprint());
        assertEquals(ApiBeanGenerator.IP, clientInfo.getIp());
    }
}
