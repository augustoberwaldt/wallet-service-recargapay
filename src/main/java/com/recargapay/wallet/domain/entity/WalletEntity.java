package com.recargapay.wallet.domain.entity;

import com.recargapay.wallet.domain.enumeration.OperationType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document("wallet")
public class WalletEntity {

    @Id
    private String id;
    private BigDecimal value;
    private LocalDateTime dateTimeOperation;
    private String document;
    private OperationType operationType;
}
