package com.recargapay.wallet.domain.entity;

import com.recargapay.wallet.domain.enumeration.OperationType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("wallet")
public class Wallet {

    private Long code;
    private BigDecimal value;
    private String document;
    private OperationType operationType;
}
