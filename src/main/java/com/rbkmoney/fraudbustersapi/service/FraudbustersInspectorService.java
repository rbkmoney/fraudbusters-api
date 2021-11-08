package com.rbkmoney.fraudbustersapi.service;

import com.rbkmoney.damsel.domain.RiskScore;
import com.rbkmoney.damsel.proxy_inspector.Context;
import com.rbkmoney.damsel.proxy_inspector.InspectorProxySrv;
import com.rbkmoney.fraudbustersapi.exceptions.RemoteInvocationException;
import com.rbkmoney.swag.fraudbusters.model.RiskScoreResult;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudbustersInspectorService {

    private final InspectorProxySrv.Iface proxyInspectorSrv;

    public com.rbkmoney.swag.fraudbusters.model.RiskScore inspectPayment(Context context) {
        try {
            RiskScore riskScore = proxyInspectorSrv.inspectPayment(context);
            RiskScoreResult riskScoreResult = new RiskScoreResult();
            riskScoreResult.setResult(com.rbkmoney.swag.fraudbusters.model.RiskScore.fromValue(riskScore.name()));
            return com.rbkmoney.swag.fraudbusters.model.RiskScore.fromValue(riskScore.name());
        } catch (TException e) {
            throw new RemoteInvocationException(e);
        }
    }
}
