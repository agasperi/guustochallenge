package com.aega.guusto.infrastructure.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aega.guusto.infrastructure.model.GiftcardBuyRequest;
import com.aega.guusto.infrastructure.model.GiftcardBuyResponse;
import com.aega.guusto.infrastructure.model.GiftcardTransaction;

import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/guusto-service/buy-gift")
public class GiftcardController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_NDJSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_NDJSON_VALUE}
    )
    public Flux<GiftcardBuyResponse> giftcardBuy(@RequestBody Flux<GiftcardBuyRequest> body) {
        return body.
                map(b -> {
                    return GiftcardBuyResponse.builder()
                            .clientId(b.getClientId())
                            .requestId("A001")
                            .transactions(b.getTransactions().stream()
                                    .map(t -> {
                                        return GiftcardTransaction.builder()
                                                .amount(t.getAmount())
                                                .quantity(t.getQuantity())
                                                .transactionId("0001")
                                                .build();
                                    })
                                    .collect(Collectors.toList ()))
                            .build();
                });
    }

}
