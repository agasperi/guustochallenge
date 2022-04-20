package com.aega.guusto.infrastructure.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;


@Getter
@Builder
@Jacksonized
@EqualsAndHashCode
public class GiftcardTransactionResponse {

    private BigDecimal amount;
    private long quantity;
    private String transactionId;

}
