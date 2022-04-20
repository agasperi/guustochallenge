package com.aega.guusto.application.command;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class GiftcardTransactionCommand {

    private long quantity;
    private BigDecimal amount;

}
