package com.recargapay.wallet.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletHistoryFilterRequest {

    private String idWallet;
    private LocalDateTime dateTimeOperation;
}
