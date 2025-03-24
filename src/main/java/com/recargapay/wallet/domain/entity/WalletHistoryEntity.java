package com.recargapay.wallet.domain.entity;

import com.recargapay.wallet.domain.enumeration.OperationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document("walletHistory")
public class WalletHistoryEntity {

    @Id
    private String id;
    private String idWallet;
    private BigDecimal value;
    private OperationType operationType;
    private LocalDateTime dateTimeOperation;
}
