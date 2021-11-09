package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.proxy_inspector.Context;
import com.rbkmoney.fraudbustersapi.utils.ApiBeanGenerator;
import com.rbkmoney.swag.fraudbusters.model.PaymentInspectRequest;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PaymentInspectRequestToContextConverterTest {

    @Test
    void convertTest() throws TException {
        PaymentInspectRequestToContextConverter paymentInspectRequestToContextConverter =
                new PaymentInspectRequestToContextConverter(new CacheToInternalDtoConverter());

        Context context = paymentInspectRequestToContextConverter.convert(new PaymentInspectRequest()
                .context(ApiBeanGenerator.initPaymentContext()));

        assertNotNull(context);
        context.validate();
        context.getPayment().validate();
        context.getPayment().getPayment().validate();
        context.getPayment().getPayment().getCost().validate();
        context.getPayment().getParty().validate();
        context.getPayment().getShop().validate();
        context.getPayment().getInvoice().validate();
    }

}
