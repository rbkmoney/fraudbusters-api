package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.ClientInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientInfoToInternalDtoConverter
        implements Converter<com.rbkmoney.swag.fraudbusters.model.UserInfo, ClientInfo> {

    @Override
    public ClientInfo convert(com.rbkmoney.swag.fraudbusters.model.UserInfo userInfo) {
        return new ClientInfo()
                .setFingerprint(userInfo.getFingerprint())
                .setEmail(userInfo.getEmail())
                .setIp(userInfo.getIp());
        //TODO добавить телефон в thrift
    }

}
