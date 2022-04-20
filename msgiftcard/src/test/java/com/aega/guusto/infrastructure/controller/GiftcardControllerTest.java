package com.aega.guusto.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import com.aega.guusto.application.command.GiftcardBuyReturn;
import com.aega.guusto.application.command.GiftcardTransactionReturn;
import com.aega.guusto.application.handler.GiftcardBuyHandler;
import com.aega.guusto.infrastructure.dto.GiftcardBuyRequest;
import com.aega.guusto.infrastructure.dto.GiftcardBuyResponse;
import com.aega.guusto.infrastructure.dto.GiftcardTransactionRequest;
import com.aega.guusto.infrastructure.dto.GiftcardTransactionResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


public class GiftcardControllerTest {

    private static final String CLIENT_ID_1 = "1";
    private static final String REQUEST_ID_1 = "A001";
    private static final String TRANSACTION_ID_1 = "0001";

    private static WebTestClient webClient;

    @Mock
    private static GiftcardBuyHandler giftcardBuyHandler;

    @BeforeAll
    public static void setUp() {
        giftcardBuyHandler = mock(GiftcardBuyHandler.class);
        webClient = WebTestClient.bindToController(new GiftcardBuyController(giftcardBuyHandler))
                .configureClient()
                .baseUrl("/guusto-service/buy-gift")
                .build();
    }

    @Test
    public void postTest() {
        List<GiftcardTransactionRequest> transactionsRequest = new ArrayList<>();
        transactionsRequest.add(GiftcardTransactionRequest.builder()
                .amount(BigDecimal.TEN)
                .quantity(1)
                .build());
        GiftcardBuyRequest gifcardBuyRequest = GiftcardBuyRequest.builder()
                .clientId(CLIENT_ID_1)
                .transactions(transactionsRequest)
                .build();

        List<GiftcardTransactionResponse> transactionsResponse = new ArrayList<>();
        transactionsResponse.add(GiftcardTransactionResponse.builder()
                .amount(transactionsRequest.get(0).getAmount())
                .quantity(transactionsRequest.get(0).getQuantity())
                .transactionId(TRANSACTION_ID_1)
                .build());
        GiftcardBuyResponse responseExpected = GiftcardBuyResponse.builder()
                .clientId(gifcardBuyRequest.getClientId())
                .requestId(REQUEST_ID_1)
                .transactions(transactionsResponse)
                .build();
        
        List<GiftcardTransactionReturn> transactionsReturn = new ArrayList<>();
        transactionsReturn.add(GiftcardTransactionReturn.builder()
                .amount(transactionsRequest.get(0).getAmount())
                .quantity(transactionsRequest.get(0).getQuantity())
                .transactionId(TRANSACTION_ID_1)
                .build());
        GiftcardBuyReturn giftcardReturn = GiftcardBuyReturn.builder()
                .clientId(gifcardBuyRequest.getClientId())
                .requestId(REQUEST_ID_1)
                .transactions(transactionsReturn)
                .build();

        Mockito.when(giftcardBuyHandler.apply(any())).thenReturn(Flux.just(giftcardReturn));

        Flux<GiftcardBuyResponse> responseBody = webClient.post()
                .contentType(MediaType.APPLICATION_NDJSON)
                .accept(MediaType.APPLICATION_NDJSON)
                .body(BodyInserters.fromPublisher(Flux.just(gifcardBuyRequest), GiftcardBuyRequest.class))
                .exchange()
                .expectStatus().isCreated()
                .returnResult(GiftcardBuyResponse.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectNext(responseExpected)
                /*.assertNext(v -> {
                    assertEquals(responseExpected.getClientId(), v.getClientId());
                    assertEquals(responseExpected.getRequestId(), v.getRequestId());
                    assertEquals(responseExpected.getTransactions().size(), v.getTransactions().size());
                    for(int i = 0; i < responseExpected.getTransactions().size(); i++) {
                        assertEquals(responseExpected.getTransactions().get(i).getAmount(), v.getTransactions().get(i).getAmount());
                        assertEquals(responseExpected.getTransactions().get(i).getQuantity(), v.getTransactions().get(i).getQuantity());
                        assertEquals(responseExpected.getTransactions().get(i).getTransactionId(), v.getTransactions().get(i).getTransactionId());
                    }
                })*/
                .verifyComplete();
    }

}
