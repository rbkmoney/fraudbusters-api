package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.CountryCode;
import com.rbkmoney.damsel.domain.PaymentSystemRef;
import com.rbkmoney.damsel.domain.PaymentTool;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PaymentToolToInternalDtoConverter
        implements Converter<com.rbkmoney.swag.fraudbusters.model.BankCard, PaymentTool> {

    @Override
    public PaymentTool convert(com.rbkmoney.swag.fraudbusters.model.BankCard bankCard) {
        return PaymentTool.bank_card(new BankCard()
                .setToken(bankCard.getCardToken())
                .setBin(bankCard.getBin())
                .setLastDigits(bankCard.getLastDigits())
                .setBankName(bankCard.getBankName())
                .setIssuerCountry(CountryCode.valueOf(bankCard.getBinCountryCode()))
                .setPaymentSystem(new PaymentSystemRef()
                        .setId(bankCard.getPaymentSystem())
                )
        );
    }

}
