package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.ProviderInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProviderInfoToInternalDtoConverter
        implements Converter<com.rbkmoney.swag.fraudbusters.model.ProviderInfo, ProviderInfo> {

    @Override
    public ProviderInfo convert(com.rbkmoney.swag.fraudbusters.model.ProviderInfo providerInfo) {
        return new ProviderInfo()
                .setProviderId(providerInfo.getProviderId())
                .setCountry(providerInfo.getCountry())
                .setTerminalId(providerInfo.getTerminalId());
    }

}
