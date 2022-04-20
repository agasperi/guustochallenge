package com.aega.guusto.application.command;

import java.util.List;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class GiftcardBuyReturn {

    private String clientId;
    private String requestId;
    private List<GiftcardTransactionReturn> transactions;

}
