package com.aega.guusto.infrastructure.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;


@Getter
@Builder
@Jacksonized
public class GiftcardTransactionRequest {

    private long quantity;
    private BigDecimal amount; 

}
