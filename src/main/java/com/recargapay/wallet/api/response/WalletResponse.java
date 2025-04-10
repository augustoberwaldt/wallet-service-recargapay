package com.recargapay.wallet.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {

    private String id;
    private BigDecimal value;
    private String document;
    private LocalDateTime dateTimeOperation;
}
