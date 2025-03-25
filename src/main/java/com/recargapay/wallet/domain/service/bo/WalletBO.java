package com.recargapay.wallet.domain.service.bo;

import com.recargapay.wallet.domain.enumeration.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletBO {

    private String id;
    private BigDecimal value;
    private LocalDateTime dateTimeOperation;
    private String document;
    private OperationType operationType;
}
