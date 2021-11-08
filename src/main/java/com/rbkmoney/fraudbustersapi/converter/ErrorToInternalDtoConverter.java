package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.Error;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ErrorToInternalDtoConverter
        implements Converter<com.rbkmoney.swag.fraudbusters.model.Error, Error> {

    @Override
    public Error convert(com.rbkmoney.swag.fraudbusters.model.Error error) {
        if (error == null) {
            return null;
        }
        return new Error()
                .setErrorCode(error.getErrorCode())
                .setErrorReason(error.getErrorMessage());
    }

}
