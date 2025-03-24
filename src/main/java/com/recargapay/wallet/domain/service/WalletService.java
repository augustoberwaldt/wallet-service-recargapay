package com.recargapay.wallet.domain.service;

import com.recargapay.wallet.api.request.WalletHistoryFilterRequest;
import com.recargapay.wallet.domain.entity.WalletEntity;
import com.recargapay.wallet.domain.enumeration.OperationType;
import com.recargapay.wallet.domain.mapper.IWalletMapper;
import com.recargapay.wallet.domain.repository.IWalletHistoryRepository;
import com.recargapay.wallet.domain.repository.IWalletRepository;
import com.recargapay.wallet.domain.service.bo.WalletBO;
import com.recargapay.wallet.domain.service.bo.WalletTransferBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final IWalletRepository repository;

    private final IWalletHistoryRepository historyRepository;

    /**
     * Creates the Wallet
     *
     * @param walletBO
     * @return Mono<WalletCreateBO>
     */
    public Mono<WalletBO> create(WalletBO walletBO) {
        walletBO.setOperationType(OperationType.CREATION);
        walletBO.setDateTimeOperation(LocalDateTime.now());
        return Mono.just(walletBO)
                .map(IWalletMapper.INSTANCE::toWalletEntity)
                .flatMap(repository::save)
                .map(IWalletMapper.INSTANCE::toWalletBO);
    }

    /**
     * Get Balance by Wallet
     *
     * @param idWallet
     * @return Mono<WalletBO>
     */
    public Mono<WalletBO> getBalanceByWallet(String idWallet) {
        return repository.findById(idWallet)
                .map(IWalletMapper.INSTANCE::toWalletBO);
    }

    /**
     * Get WalletHistory by idWallet and  dateTimeOperation
     * Retrieve the balance of a user's wallet at a specific point in the past
     *
     * @param request
     * @return
     */
    public Mono<WalletBO> getBalanceByWalletHistory(WalletHistoryFilterRequest request) {

        return historyRepository.findByIdWalletAndDateTimeOperation(request.getIdWallet(),
                        request.getDateTimeOperation())
                .map(IWalletMapper.INSTANCE::toWalletBO);
    }

    /**
     * Deposit Funds
     *
     * @param walletBO
     * @return Mono<WalletBO>
     */
    public Mono<WalletBO> deposit(WalletBO walletBO) {

        return validate(walletBO)
                .flatMap(self -> this.getWalletEntity(self.getId()))
                .flatMap(walletEntity -> historyRepository.save(IWalletMapper.INSTANCE.toWalletHistory(walletEntity))
                        .flatMap(walletHistoryEntity -> {

                            walletEntity.setValue(walletEntity.getValue().add(walletBO.getValue()));

                            return repository.save(walletEntity);
                        }))
                .map(IWalletMapper.INSTANCE::toWalletBO);
    }

    /**
     * Withdraw Funds: Enable users to withdraw money from their wallets
     *
     * @param walletBO
     * @return Mono<WalletBO>
     */
    public Mono<WalletBO> withdraw(WalletBO walletBO) {

        return validate(walletBO)
                .flatMap(self -> this.getWalletEntity(self.getId()))
                .flatMap(walletEntity -> {
                    if (walletBO.getValue().compareTo(walletEntity.getValue()) > 0) {
                        System.out.println("Valor solicitado para saque e maior que saldo em conta");
                    }

                    return historyRepository.save(IWalletMapper.INSTANCE.toWalletHistory(walletEntity))
                            .flatMap(self -> {
                                walletEntity.setValue(walletEntity.getValue().add(walletBO.getValue()));
                                return repository.save(walletEntity);
                            });
                })
                .map(IWalletMapper.INSTANCE::toWalletBO);
    }

    /**
     * Method validate walletBO
     * validate if negative value
     * validate if value is zero
     *
     * @param walletBO
     * @return
     */
    private Mono<WalletBO> validate(WalletBO walletBO) {

        if (walletBO.getValue().signum() < 0) {
            return Mono.error(new RuntimeException("O valor nao deve ser negativo para deposito."));
        }

        if (walletBO.getValue().compareTo(BigDecimal.ZERO) == 0) {
            return Mono.error(new RuntimeException("O valor nao deve ser igual zero."));
        }
        return Mono.just(walletBO);
    }

    /**
     * Get Object Entity in Database
     *
     * @param idWallet
     * @return
     */
    private Mono<WalletEntity> getWalletEntity(String idWallet) {
        return repository.findById(idWallet)
                .switchIfEmpty(Mono.error(new RuntimeException("Carteira nao encontrada para deposito.")));
    }

    public Mono<WalletTransferBO> transfer(WalletTransferBO walletTransferBO) {

        return getWalletEntity(walletTransferBO.getIdWallet())
                .flatMap(self -> getWalletTransferBOMono(walletTransferBO, self));
    }

    private Mono<WalletTransferBO> getWalletTransferBOMono(WalletTransferBO walletTransferBO, WalletEntity wallet) {
        return repository.findById(walletTransferBO.getIdTransferWallet())
                .switchIfEmpty(Mono.error(new RuntimeException("Carteira ha ser enviada nao encontrata.")))
                .flatMap();
    }
}
