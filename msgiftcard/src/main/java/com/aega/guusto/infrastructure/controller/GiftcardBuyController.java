package com.aega.guusto.infrastructure.controller;

import com.aega.guusto.application.handler.GiftcardBuyHandler;
import com.aega.guusto.infrastructure.dto.GiftcardBuyRequest;
import com.aega.guusto.infrastructure.dto.GiftcardBuyResponse;
import com.aega.guusto.infrastructure.mapper.GiftcardBuyDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/guusto-service")
@RequiredArgsConstructor
public class GiftcardBuyController {

    private final GiftcardBuyHandler giftcardBuyHandler;

    private static final GiftcardBuyDtoMapper MAPPER = GiftcardBuyDtoMapper.INSTANCE;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
        value = "/buy-gift",
        consumes = {MediaType.APPLICATION_NDJSON_VALUE},
        produces = {MediaType.APPLICATION_NDJSON_VALUE}
    )
    public Flux<GiftcardBuyResponse> giftcardBuy(@RequestBody Flux<GiftcardBuyRequest> requestBody) {
        return giftcardBuyHandler.apply(MAPPER.fluxFromRequestToCommand(requestBody))
                .map(MAPPER::fromReturnToResponse);
    }

}
