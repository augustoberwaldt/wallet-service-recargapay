package com.recargapay.wallet.domain.mapper;

import com.recargapay.wallet.api.request.WalletCreateRequest;
import com.recargapay.wallet.api.request.WalletRequest;
import com.recargapay.wallet.api.request.WalletTransferRequest;
import com.recargapay.wallet.api.response.WalletResponse;
import com.recargapay.wallet.domain.entity.WalletEntity;
import com.recargapay.wallet.domain.entity.WalletHistoryEntity;
import com.recargapay.wallet.domain.service.bo.WalletBO;
import com.recargapay.wallet.domain.service.bo.WalletTransferBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;

@Mapper
public interface IWalletMapper {

    IWalletMapper INSTANCE = Mappers.getMapper(IWalletMapper.class);

    WalletBO toWalletBO(WalletCreateRequest walletRequest);

    WalletBO toWalletBO(WalletRequest walletRequest);

    WalletBO toWalletBO(WalletEntity walletEntity);


    default WalletBO toWalletBO(WalletHistoryEntity walletHistoryEntity) {

        return WalletBO.builder()
                .operationType(walletHistoryEntity.getOperationType())
                .id(walletHistoryEntity.getIdWallet())
                .value(walletHistoryEntity.getValue())
                .document(walletHistoryEntity.getDocument())
                .build();
    }

    WalletResponse toWalletResponse(WalletBO walletBO);

    WalletEntity toWalletEntity(WalletBO walletBO);

    WalletTransferBO toWalletTransferBO(WalletTransferRequest walletTransferRequest);


    WalletHistoryEntity toWalletHistory(WalletEntity walletEntity);

    WalletResponse toWalletTransferResponse(WalletTransferBO walletTransferBO);
}
