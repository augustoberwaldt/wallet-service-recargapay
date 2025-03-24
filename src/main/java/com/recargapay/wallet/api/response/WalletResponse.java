package com.recargapay.wallet.api.response;

import com.recargapay.wallet.domain.enumeration.OperationType;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class WalletResponse {

    private BigDecimal value;
    private String document;
    private OperationType operationType;

}
