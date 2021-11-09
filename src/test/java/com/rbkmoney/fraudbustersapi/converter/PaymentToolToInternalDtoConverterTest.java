package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.PaymentTool;
import com.rbkmoney.fraudbustersapi.utils.ApiBeanGenerator;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PaymentToolToInternalDtoConverterTest {

    @Test
    void convert() throws TException {
        PaymentToolToInternalDtoConverter converter =
                new PaymentToolToInternalDtoConverter(new BankCardToInternalDtoConverter());

        PaymentTool paymentTool = converter.convert(ApiBeanGenerator.initBankCard());

        assertNotNull(paymentTool);

        BankCard bankCard = paymentTool.getBankCard();
        bankCard.validate();

        assertEquals(ApiBeanGenerator.BANK_NAME, bankCard.bank_name);
        assertEquals(ApiBeanGenerator.BIN, bankCard.bin);
        assertEquals(ApiBeanGenerator.CARD_TOKEN, bankCard.token);
        assertEquals(ApiBeanGenerator.LAST_DIGITS, bankCard.last_digits);
    }
}
