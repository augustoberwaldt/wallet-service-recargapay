package com.recargapay.wallet.domain.service.bo;

import com.recargapay.wallet.domain.enumeration.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransferBO {
    private String idWallet;
    private String idTransferWallet;
    private BigDecimal value;
    private OperationType operationType;
}
