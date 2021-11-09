package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.Error;
import com.rbkmoney.fraudbustersapi.utils.ApiBeanGenerator;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ErrorToInternalDtoConverterTest {

    @Test
    void convert() throws TException {
        ErrorToInternalDtoConverter errorToInternalDtoConverter = new ErrorToInternalDtoConverter();

        Error error = errorToInternalDtoConverter.convert(ApiBeanGenerator.initError());

        assertNotNull(error);
        error.validate();

        assertEquals(ApiBeanGenerator.ERROR_CODE, error.getErrorCode());
        assertEquals(ApiBeanGenerator.ERROR_REASON, error.getErrorReason());
    }
}
