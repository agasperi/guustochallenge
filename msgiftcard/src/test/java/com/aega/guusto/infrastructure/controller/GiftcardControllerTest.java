package com.aega.guusto.infrastructure.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.aega.guusto.infrastructure.model.GiftcardBuyRequest;
import com.aega.guusto.infrastructure.model.GiftcardBuyResponse;
import com.aega.guusto.infrastructure.model.GiftcardTransaction;
import com.aega.guusto.infrastructure.model.GiftcardTransactionRequest;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


public class GiftcardControllerTest {
    
    private static final String CLIENT_ID_1 = "1";

    private static WebTestClient webClient;

    @BeforeAll
    public static void setUp() {
        webClient = WebTestClient.bindToController(new GiftcardController())
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
        Flux<GiftcardBuyRequest> gifcardToRequest = Flux.just(GiftcardBuyRequest.builder()
                .clientId(CLIENT_ID_1)
                .transactions(transactionsRequest)
                .build());

        List<GiftcardTransaction> transactionsResponse = new ArrayList<>();
        transactionsResponse.add(GiftcardTransaction.builder()
                .amount(transactionsRequest.get(0).getAmount())
                .quantity(transactionsRequest.get(0).getQuantity())
                .transactionId("0001")
                .build());
        GiftcardBuyResponse responseExpected = GiftcardBuyResponse.builder()
                .clientId(CLIENT_ID_1)
                .requestId("A001")
                .transactions(transactionsResponse)
                .build();

        Flux<GiftcardBuyResponse> responseBody = webClient.post()
                .contentType(MediaType.APPLICATION_NDJSON)
                .accept(MediaType.APPLICATION_NDJSON)
                .body(BodyInserters.fromPublisher(gifcardToRequest, GiftcardBuyRequest.class))
                .exchange()
                .expectStatus().isCreated()
                .returnResult(GiftcardBuyResponse.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectNext(responseExpected)
                .verifyComplete();
    }

}
