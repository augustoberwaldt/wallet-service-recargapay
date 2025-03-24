package com.recargapay.wallet.api;


import com.recargapay.wallet.api.request.WalletCreateRequest;
import com.recargapay.wallet.api.request.WalletHistoryFilterRequest;
import com.recargapay.wallet.api.request.WalletRequest;
import com.recargapay.wallet.api.request.WalletTransferRequest;
import com.recargapay.wallet.api.response.WalletResponse;
import com.recargapay.wallet.domain.mapper.IWalletMapper;
import com.recargapay.wallet.domain.service.WalletService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("wallet")
public class WalletApi {

    private final WalletService service;

    public WalletApi(WalletService service) {
        this.service = service;
    }

    @PostMapping("create")
    public Mono<WalletResponse> create(@RequestBody WalletCreateRequest request) {

        return Mono.just(request)
                .map(IWalletMapper.INSTANCE::toWalletBO)
                .flatMap(service::create)
                .map(IWalletMapper.INSTANCE::toWalletResponse);
    }

    @PostMapping("balance/{idWallet}")
    public Mono<WalletResponse> balance(@PathVariable String idWallet) {

        return service.getBalanceByWallet(idWallet)
                .map(IWalletMapper.INSTANCE::toWalletResponse);
    }

    @PostMapping("balance-by-history")
    public Mono<WalletResponse> balanceHistory(@Parameter WalletHistoryFilterRequest request) {

        return service.getBalanceByWalletHistory(request)
                .map(IWalletMapper.INSTANCE::toWalletResponse);
    }


    @PostMapping("deposit")
    public Mono<WalletResponse> deposit(@RequestBody WalletRequest request) {

        return Mono.just(request)
                .map(IWalletMapper.INSTANCE::toWalletBO)
                .flatMap(service::deposit)
                .map(IWalletMapper.INSTANCE::toWalletResponse);
    }


    @PostMapping("withdraw")
    public Mono<WalletResponse> withdraw(@RequestBody WalletRequest request) {

        return Mono.just(request)
                .map(IWalletMapper.INSTANCE::toWalletBO)
                .flatMap(service::withdraw)
                .map(IWalletMapper.INSTANCE::toWalletResponse);
    }

    @PostMapping("transfer")
    public Mono<WalletResponse> transfer(@RequestBody WalletTransferRequest request) {

        return Mono.just(request)
                .map(IWalletMapper.INSTANCE::toWalletTransferBO)
                .flatMap(service::transfer)
                .map(IWalletMapper.INSTANCE::toWalletResponse);
    }

}
