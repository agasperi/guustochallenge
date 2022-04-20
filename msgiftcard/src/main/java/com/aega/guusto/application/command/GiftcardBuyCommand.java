package com.aega.guusto.application.command;

import java.util.List;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class GiftcardBuyCommand {

    private String clientId;
    private List<GiftcardTransactionCommand> transactions;

}
