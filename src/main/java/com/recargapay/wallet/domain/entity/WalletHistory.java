package com.recargapay.wallet.domain.entity;

import com.recargapay.wallet.domain.enumeration.OperationType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("walletHistory")
public class WalletHistory {

    private Long code;
    private BigDecimal value;
    private OperationType operationType;
}
