package com.recargapay.wallet.domain.service;

import com.recargapay.wallet.domain.service.bo.WalletBO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class WalletService {

    public Mono<WalletBO> create(WalletBO walletBO) {
        return  null;
    }
}
