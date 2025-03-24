package com.recargapay.wallet.domain.service.bo;

import com.recargapay.wallet.domain.enumeration.OperationType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WalletTransferBO {
    private String idWallet;
    private String idTransferWallet;
    private BigDecimal value;
    private OperationType operationType;
}
