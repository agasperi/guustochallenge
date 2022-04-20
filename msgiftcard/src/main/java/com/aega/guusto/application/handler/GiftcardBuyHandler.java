package com.aega.guusto.application.handler;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.aega.guusto.application.command.GiftcardBuyCommand;
import com.aega.guusto.application.command.GiftcardBuyReturn;
import com.aega.guusto.application.command.GiftcardTransactionReturn;
import com.aega.guusto.cqrs.application.CommandOperate;

import reactor.core.publisher.Flux;


@Component
public class GiftcardBuyHandler implements CommandOperate<Flux<GiftcardBuyCommand>,Flux<GiftcardBuyReturn>> {

    public Flux<GiftcardBuyReturn> apply(Flux<GiftcardBuyCommand> command) {
        return command.
                map(c -> {
                    return GiftcardBuyReturn.builder()
                            .clientId(c.getClientId())
                            .requestId("A001")
                            .transactions(c.getTransactions()
                                    .stream()
                                    .map(t -> {
                                        return GiftcardTransactionReturn.builder()
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
