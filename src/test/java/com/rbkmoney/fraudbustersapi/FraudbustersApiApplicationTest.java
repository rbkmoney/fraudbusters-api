package com.rbkmoney.fraudbustersapi;

import com.rbkmoney.damsel.domain.RiskScore;
import com.rbkmoney.damsel.fraudbusters.PaymentServiceSrv;
import com.rbkmoney.damsel.proxy_inspector.InspectorProxySrv;
import com.rbkmoney.swag.fraudbusters.model.*;
import org.apache.thrift.TException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FraudbustersApiApplication.class)
public class FraudbustersApiApplicationTest {

    public static final String FRAUDBUSTERS_CHARGEBACKS = "/fraudbusters/chargebacks";
    public static final String FRAUDBUSTERS_REFUNDS = "/fraudbusters/refunds";
    public static final String FRAUDBUSTERS_PAYMENTS = "/fraudbusters/payments";
    public static final String FRAUDBUSTERS_FRAUD_PAYMENTS = "/fraudbusters/fraud-payments";
    public static final String FRAUDBUSTERS_WITHDRAWALS = "/fraudbusters/withdrawals";
    public static final String FRAUDBUSTERS_INSPECT_PAYMENTS = "/fraudbusters/inspect-payment";
    public static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    int serverPort;

    @MockBean
    PaymentServiceSrv.Iface paymentServiceSrv;
    @MockBean
    InspectorProxySrv.Iface proxyInspectorSrv;

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void insertChargebacksTest() throws TException {
        ResponseEntity<Void> voidResponseEntity =
                restTemplate.postForEntity(BASE_URL + serverPort + FRAUDBUSTERS_CHARGEBACKS,
                        new ChargebacksRequest(), Void.class);

        verify(paymentServiceSrv, times(1)).insertChargebacks(any());
    }

    @Test
    public void insertRefundsTest() throws TException {
        ResponseEntity<Void> voidResponseEntity =
                restTemplate.postForEntity(BASE_URL + serverPort + FRAUDBUSTERS_REFUNDS,
                        new RefundsRequest(), Void.class);

        verify(paymentServiceSrv, times(1)).insertRefunds(any());
    }

    @Test
    public void insertPaymentsTest() throws TException {
        ResponseEntity<Void> voidResponseEntity =
                restTemplate.postForEntity(BASE_URL + serverPort + FRAUDBUSTERS_PAYMENTS,
                        new PaymentsChangesRequest(), Void.class);

        verify(paymentServiceSrv, times(1)).insertPayments(any());
    }

    @Test
    public void insertFraudPaymentsTest() throws TException {
        ResponseEntity<Void> voidResponseEntity =
                restTemplate.postForEntity(BASE_URL + serverPort + FRAUDBUSTERS_FRAUD_PAYMENTS,
                        new FraudPaymentsRequest(), Void.class);

        verify(paymentServiceSrv, times(1)).insertFraudPayments(any());
    }

    @Test
    public void insertWithdrawalsTest() throws TException {
        ResponseEntity<Void> voidResponseEntity =
                restTemplate.postForEntity(BASE_URL + serverPort + FRAUDBUSTERS_WITHDRAWALS,
                        new WithdrawalsRequest(), Void.class);

        verify(paymentServiceSrv, times(1)).insertWithdrawals(any());
    }

    @Test
    public void inspectPaymentsTest() throws TException {
        when(proxyInspectorSrv.inspectPayment(any())).thenReturn(RiskScore.fatal);

        ResponseEntity<RiskScoreResult> response =
                restTemplate.postForEntity(BASE_URL + serverPort + FRAUDBUSTERS_INSPECT_PAYMENTS,
                        new PaymentInspectRequest(), RiskScoreResult.class);

        verify(proxyInspectorSrv, times(1)).inspectPayment(any());
        assertEquals(com.rbkmoney.swag.fraudbusters.model.RiskScore.FATAL,
                Objects.requireNonNull(response.getBody()).getResult());
    }
}
