package com.rbkmoney.fraudbustersapi.configuration;

import com.rbkmoney.damsel.fraudbusters.PaymentServiceSrv;
import com.rbkmoney.damsel.proxy_inspector.InspectorProxySrv;
import com.rbkmoney.woody.thrift.impl.http.THSpawnClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class FraudbustersConfig {

    @Bean
    public PaymentServiceSrv.Iface paymentServiceSrv(@Value("${fraudbusters.service.payment.url}") Resource resource,
                                                     @Value("${fraudbusters.service.payment.networkTimeout}") int networkTimeout)
            throws IOException {
        return new THSpawnClientBuilder()
                .withNetworkTimeout(networkTimeout)
                .withAddress(resource.getURI()).build(PaymentServiceSrv.Iface.class);
    }

    @Bean
    public InspectorProxySrv.Iface proxyInspectorSrv(@Value("${fraudbusters.service.inspector.url}") Resource resource,
                                                     @Value("${fraudbusters.service.inspector.networkTimeout}") int networkTimeout)
            throws IOException {
        return new THSpawnClientBuilder()
                .withNetworkTimeout(networkTimeout)
                .withAddress(resource.getURI()).build(InspectorProxySrv.Iface.class);
    }

}
