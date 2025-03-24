package com.recargapay.wallet.api.request;

import com.recargapay.wallet.domain.enumeration.OperationType;

import java.math.BigDecimal;

public class WalletRequest {

    private BigDecimal value;
    private String document;
    private OperationType operationType;

}
