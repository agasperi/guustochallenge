package com.aega.guusto.infrastructure.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;


@Getter
@Builder
@Jacksonized
@EqualsAndHashCode
public class GiftcardTransaction {

    private BigDecimal amount;
    private long quantity;
    private String transactionId;

}
