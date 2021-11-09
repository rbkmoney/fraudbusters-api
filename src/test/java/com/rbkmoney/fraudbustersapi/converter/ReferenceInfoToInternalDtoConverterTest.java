package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.MerchantInfo;
import com.rbkmoney.damsel.fraudbusters.ReferenceInfo;
import com.rbkmoney.fraudbustersapi.utils.ApiBeanGenerator;
import org.apache.thrift.TException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReferenceInfoToInternalDtoConverterTest {

    @Test
    void convert() throws TException {
        ReferenceInfoToInternalDtoConverter converter = new ReferenceInfoToInternalDtoConverter();

        ReferenceInfo referenceInfo = converter.convert(ApiBeanGenerator.initMerachantInfo());

        assertNotNull(referenceInfo);
        MerchantInfo merchantInfo = referenceInfo.getMerchantInfo();
        merchantInfo.validate();

        assertEquals(ApiBeanGenerator.PARTY_ID, merchantInfo.getPartyId());
        assertEquals(ApiBeanGenerator.SHOP_ID, merchantInfo.getShopId());
    }
}
