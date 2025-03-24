package com.recargapay.wallet.domain.repository;


import com.recargapay.wallet.domain.entity.WalletHistoryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface IWalletHistoryRepository extends ReactiveCrudRepository<WalletHistoryEntity, String> {

    Mono<WalletHistoryEntity> findByIdWalletAndDateTimeOperation(String id, LocalDateTime dateTimeOperation);
}
