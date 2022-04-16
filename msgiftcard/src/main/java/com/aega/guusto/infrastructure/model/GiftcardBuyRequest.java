package com.aega.guusto.infrastructure.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;


@Getter
@Builder
@Jacksonized
public class GiftcardBuyRequest {

    private String clientId;
    private List<GiftcardTransactionRequest> transactions;

}
