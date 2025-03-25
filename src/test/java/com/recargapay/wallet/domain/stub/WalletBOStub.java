package com.recargapay.wallet.domain.stub;

import com.recargapay.wallet.domain.enumeration.OperationType;
import com.recargapay.wallet.domain.service.bo.WalletBO;

public class WalletBOStub {


    public static WalletBO create() {

        return WalletBO.builder()
                .operationType(OperationType.CREATION)
                .build();
    }
}
