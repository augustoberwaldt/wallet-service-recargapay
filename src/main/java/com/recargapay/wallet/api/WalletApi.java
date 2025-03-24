package com.recargapay.wallet.api;


import com.recargapay.wallet.api.request.WalletRequest;
import com.recargapay.wallet.api.response.WalletResponse;
import com.recargapay.wallet.domain.mapper.IWalletMapper;
import com.recargapay.wallet.domain.service.WalletService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("wallet")
public class WalletApi {

    private final WalletService service;

    public WalletApi(WalletService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<WalletResponse> create(@RequestBody WalletRequest request) {

        return Mono.just(request)
                .map(IWalletMapper.INSTANCE::toWalletBO)
                .flatMap(service::create)
                .map(IWalletMapper.INSTANCE::toWalletResponse);
    }

}
