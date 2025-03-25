package com.recargapay.wallet.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.recargapay.wallet.domain.entity.WalletEntity;
import com.recargapay.wallet.domain.entity.WalletHistoryEntity;
import com.recargapay.wallet.domain.mapper.IWalletMapper;
import com.recargapay.wallet.domain.repository.IWalletHistoryRepository;
import com.recargapay.wallet.domain.repository.IWalletRepository;
import com.recargapay.wallet.domain.service.bo.WalletBO;
import com.recargapay.wallet.domain.service.bo.WalletTransferBO;
import com.recargapay.wallet.domain.stub.WalletBOStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @InjectMocks
    private WalletService walletService;
    @Mock
    private IWalletRepository walletRepository;
    @Mock
    private IWalletHistoryRepository walletHistoryRepository;
    @Spy
    IWalletMapper mapper = IWalletMapper.INSTANCE;

    @Test
    void createWalletSuccessfully() {
        WalletBO walletBO = WalletBO.builder().build();
        WalletEntity walletEntity = new WalletEntity();
        when(walletRepository.save(any(WalletEntity.class))).thenReturn(Mono.just(walletEntity));
        when(IWalletMapper.INSTANCE.toWalletEntity(walletBO)).thenReturn(walletEntity);
        when(IWalletMapper.INSTANCE.toWalletBO(walletEntity)).thenReturn(walletBO);

        StepVerifier.create(walletService.create(walletBO))
                .expectNext(walletBO)
                .verifyComplete();
    }

    @Test
    void getBalanceByWalletSuccessfully() {
        String idWallet = "walletId";
        WalletEntity walletEntity = new WalletEntity();
        WalletBO walletBO = WalletBO.builder().build();
        when(walletRepository.findById(idWallet)).thenReturn(Mono.just(walletEntity));
        when(IWalletMapper.INSTANCE.toWalletBO(walletEntity)).thenReturn(walletBO);

        StepVerifier.create(walletService.getBalanceByWallet(idWallet))
                .expectNext(walletBO)
                .verifyComplete();
    }

    @Test
    void depositFundsSuccessfully() {
        WalletBO walletBO = WalletBO.builder().build();
        walletBO.setValue(BigDecimal.valueOf(100));
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setValue(BigDecimal.valueOf(50));
        when(walletRepository.findById(walletBO.getId())).thenReturn(Mono.just(walletEntity));
        when(walletHistoryRepository.save(any())).thenReturn(Mono.just(new WalletHistoryEntity()));
        when(walletRepository.save(walletEntity)).thenReturn(Mono.just(walletEntity));
        when(IWalletMapper.INSTANCE.toWalletBO(walletEntity)).thenReturn(walletBO);

        StepVerifier.create(walletService.deposit(walletBO))
                .expectNext(walletBO)
                .verifyComplete();
    }

    @Test
    void withdrawFundsSuccessfully() {
        WalletBO walletBO = WalletBOStub.create();
        walletBO.setValue(BigDecimal.valueOf(50));
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setValue(BigDecimal.valueOf(100));
        when(walletRepository.findById(walletBO.getId())).thenReturn(Mono.just(walletEntity));
        when(walletHistoryRepository.save(any())).thenReturn(Mono.just(new WalletHistoryEntity()));
        when(walletRepository.save(walletEntity)).thenReturn(Mono.just(walletEntity));
        when(IWalletMapper.INSTANCE.toWalletBO(walletEntity)).thenReturn(walletBO);

        StepVerifier.create(walletService.withdraw(walletBO))
                .expectNext(walletBO)
                .verifyComplete();
    }

    @Test
    void transferFundsSuccessfully() {
        WalletTransferBO walletTransferBO = new WalletTransferBO();
        walletTransferBO.setValue(BigDecimal.valueOf(50));
        WalletEntity sourceWallet = new WalletEntity();
        sourceWallet.setValue(BigDecimal.valueOf(100));
        WalletEntity targetWallet = new WalletEntity();
        targetWallet.setValue(BigDecimal.valueOf(50));
        when(walletRepository.findById(walletTransferBO.getIdWallet())).thenReturn(Mono.just(sourceWallet));
        when(walletRepository.findById(walletTransferBO.getIdTransferWallet())).thenReturn(Mono.just(targetWallet));
        when(walletHistoryRepository.save(any())).thenReturn(Mono.just(new WalletHistoryEntity()));
        when(walletRepository.save(sourceWallet)).thenReturn(Mono.just(sourceWallet));
        when(walletRepository.save(targetWallet)).thenReturn(Mono.just(targetWallet));

        StepVerifier.create(walletService.transfer(walletTransferBO))
                .expectNext(walletTransferBO)
                .verifyComplete();
    }

    @Test
    void transferFundsTargetWalletNotFound() {
        WalletTransferBO walletTransferBO = new WalletTransferBO();
        walletTransferBO.setValue(BigDecimal.valueOf(50));
        WalletEntity sourceWallet = new WalletEntity();
        sourceWallet.setValue(BigDecimal.valueOf(100));
        when(walletRepository.findById(walletTransferBO.getIdWallet())).thenReturn(Mono.just(sourceWallet));
        when(walletRepository.findById(walletTransferBO.getIdTransferWallet())).thenReturn(Mono.empty());

        StepVerifier.create(walletService.transfer(walletTransferBO))
                .expectErrorMessage("Carteira nao encontrada para deposito.")
                .verify();
    }
}