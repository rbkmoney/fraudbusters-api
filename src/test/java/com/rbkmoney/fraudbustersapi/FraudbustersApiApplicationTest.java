package com.rbkmoney.fraudbustersapi;

import com.rbkmoney.damsel.fraudbusters.PaymentServiceSrv;
import com.rbkmoney.damsel.proxy_inspector.InspectorProxySrv;
import com.rbkmoney.fraudbustersapi.service.FraudbustersDataService;
import com.rbkmoney.fraudbustersapi.service.FraudbustersInspectorService;
import com.rbkmoney.swag.fraudbusters.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.rbkmoney.fraudbustersapi.utils.ApiBeanGenerator.*;
import static org.junit.Assert.assertThrows;
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
    FraudbustersDataService fraudbustersDataService;
    @MockBean
    FraudbustersInspectorService fraudbustersInspectorService;
    @MockBean
    PaymentServiceSrv.Iface paymentServiceSrv;
    @MockBean
    InspectorProxySrv.Iface proxyInspectorSrv;

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void insertChargebacksTest() {
        ChargebacksRequest request = new ChargebacksRequest();
        restTemplate.postForEntity(initUrl(FRAUDBUSTERS_CHARGEBACKS), request, Void.class);
        verify(fraudbustersDataService, times(0)).insertChargebacks(any());

        Chargeback chargeback = new Chargeback();
        request.setChargebacks(List.of(chargeback));
        assertThrows(HttpClientErrorException.BadRequest.class,
                () -> restTemplate.postForEntity(initUrl(FRAUDBUSTERS_CHARGEBACKS), request, Void.class)
        );

        chargeback = initChargeback();
        ChargebacksRequest requestFull = new ChargebacksRequest()
                .chargebacks(List.of(chargeback));
        restTemplate.postForEntity(initUrl(FRAUDBUSTERS_CHARGEBACKS), requestFull, Void.class);
        verify(fraudbustersDataService, times(1)).insertChargebacks(any());
    }

    @Test
    public void insertRefundsTest() {
        RefundsRequest request = new RefundsRequest();
        restTemplate.postForEntity(initUrl(FRAUDBUSTERS_REFUNDS), request, Void.class);
        verify(fraudbustersDataService, times(0)).insertRefunds(any());

        request.refunds(List.of(new Refund()));
        assertThrows(HttpClientErrorException.BadRequest.class,
                () -> restTemplate.postForEntity(initUrl(FRAUDBUSTERS_REFUNDS), request, Void.class)
        );

        request.refunds(List.of(initRefund()));
        restTemplate.postForEntity(initUrl(FRAUDBUSTERS_REFUNDS), request, Void.class);
        verify(fraudbustersDataService, times(1)).insertRefunds(any());
    }

    private String initUrl(String fraudbustersRefunds) {
        return BASE_URL + serverPort + fraudbustersRefunds;
    }

    @Test
    public void insertPaymentsTest() {
        PaymentsChangesRequest request = new PaymentsChangesRequest();
        restTemplate.postForEntity(initUrl(FRAUDBUSTERS_PAYMENTS), request, Void.class);
        verify(fraudbustersDataService, times(0)).insertPaymentsChanges(any());

        request.paymentsChanges(List.of(new PaymentChange()));
        assertThrows(HttpClientErrorException.BadRequest.class,
                () -> restTemplate.postForEntity(initUrl(FRAUDBUSTERS_PAYMENTS), request, Void.class)
        );

        request.paymentsChanges(List.of(initPaymentChange()));
        restTemplate.postForEntity(initUrl(FRAUDBUSTERS_PAYMENTS), request, Void.class);
        verify(fraudbustersDataService, times(1)).insertPaymentsChanges(any());
    }

    @Test
    public void insertFraudPaymentsTest() {
        FraudPaymentsRequest request = new FraudPaymentsRequest();
        restTemplate.postForEntity(initUrl(FRAUDBUSTERS_FRAUD_PAYMENTS), request, Void.class);
        verify(fraudbustersDataService, times(0)).insertFraudPayments(any());

        request.fraudPayments(List.of(new FraudPayment()));
        assertThrows(HttpClientErrorException.BadRequest.class,
                () -> restTemplate.postForEntity(initUrl(FRAUDBUSTERS_FRAUD_PAYMENTS), request, Void.class)
        );

        request.fraudPayments(List.of(initFraudPayment()));
        restTemplate.postForEntity(initUrl(FRAUDBUSTERS_FRAUD_PAYMENTS), request, Void.class);
        verify(fraudbustersDataService, times(1)).insertFraudPayments(any());
    }

    @Test
    public void insertWithdrawalsTest() {
        WithdrawalsRequest request = new WithdrawalsRequest();
        restTemplate.postForEntity(initUrl(FRAUDBUSTERS_WITHDRAWALS), request, Void.class);
        verify(fraudbustersDataService, times(0)).insertWithdrawals(any());

        request.withdrawals(List.of(new Withdrawal()));
        assertThrows(HttpClientErrorException.BadRequest.class,
                () -> restTemplate.postForEntity(initUrl(FRAUDBUSTERS_WITHDRAWALS), request, Void.class)
        );

        request.withdrawals(List.of(initWithdrawal()));
        restTemplate.postForEntity(initUrl(FRAUDBUSTERS_WITHDRAWALS), request, Void.class);
        verify(fraudbustersDataService, times(1)).insertWithdrawals(any());
    }

    @Test
    public void inspectPaymentsTest() {
        when(fraudbustersInspectorService.inspectPayment(any())).thenReturn(RiskScore.FATAL);

        PaymentInspectRequest request = new PaymentInspectRequest();
        assertThrows(HttpClientErrorException.BadRequest.class, () ->
                restTemplate.postForEntity(initUrl(FRAUDBUSTERS_INSPECT_PAYMENTS), request, RiskScoreResult.class));

        request.context(new PaymentContext());
        assertThrows(HttpClientErrorException.BadRequest.class,
                () -> restTemplate.postForEntity(initUrl(FRAUDBUSTERS_INSPECT_PAYMENTS), request, RiskScoreResult.class)
        );

        request.context(initPaymentContext());
        restTemplate.postForEntity(initUrl(FRAUDBUSTERS_INSPECT_PAYMENTS), request, RiskScoreResult.class);
        verify(fraudbustersInspectorService, times(1)).inspectPayment(any());
    }

}
