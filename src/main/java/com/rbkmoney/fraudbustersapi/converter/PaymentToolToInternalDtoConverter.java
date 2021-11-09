package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.domain.PaymentTool;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentToolToInternalDtoConverter
        implements Converter<com.rbkmoney.swag.fraudbusters.model.BankCard, PaymentTool> {

    private final BankCardToInternalDtoConverter bankCardToInternalDtoConverter;

    @Override
    public PaymentTool convert(com.rbkmoney.swag.fraudbusters.model.BankCard bankCard) {
        return PaymentTool.bank_card(bankCardToInternalDtoConverter.convert(bankCard));
    }

}
