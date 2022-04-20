package com.aega.guusto.application.command;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class GiftcardTransactionReturn {

    private BigDecimal amount;
    private long quantity;
    private String transactionId;

}
