package com.aega.guusto.infrastructure.model;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;


@Getter
@Builder
@Jacksonized
@EqualsAndHashCode
public class GiftcardBuyResponse {

    private String clientId;
    private String requestId;
    private List<GiftcardTransaction> transactions;

}
