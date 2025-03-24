package com.recargapay.wallet.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletCreateRequest {

    private BigDecimal value;
    private String document;
}
