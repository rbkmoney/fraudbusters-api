package com.rbkmoney.fraudbustersapi.converter;

import com.rbkmoney.damsel.fraudbusters.MerchantInfo;
import com.rbkmoney.damsel.fraudbusters.ReferenceInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReferenceInfoToInternalDtoConverter
        implements Converter<com.rbkmoney.swag.fraudbusters.model.MerchantInfo, ReferenceInfo> {

    @Override
    public ReferenceInfo convert(com.rbkmoney.swag.fraudbusters.model.MerchantInfo merchantInfo) {
        ReferenceInfo referenceInfo = new ReferenceInfo();
        referenceInfo.setMerchantInfo(new MerchantInfo()
                .setPartyId(merchantInfo.getPartyId())
                .setShopId(merchantInfo.getShopId()));
        return referenceInfo;
    }

}
