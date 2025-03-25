package com.recargapay.wallet.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransferResponse {

    private String idWallet;
    private String idTransferWallet;
    private BigDecimal value;
}
