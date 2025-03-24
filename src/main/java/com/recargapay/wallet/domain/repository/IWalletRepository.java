package com.recargapay.wallet.domain.repository;


import com.recargapay.wallet.domain.entity.WalletEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWalletRepository extends ReactiveCrudRepository<WalletEntity, String> {


}
