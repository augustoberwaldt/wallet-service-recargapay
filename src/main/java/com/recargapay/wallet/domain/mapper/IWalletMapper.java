package com.recargapay.wallet.domain.mapper;

import com.recargapay.wallet.api.request.WalletRequest;
import com.recargapay.wallet.api.response.WalletResponse;
import com.recargapay.wallet.domain.service.bo.WalletBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IWalletMapper {
    IWalletMapper INSTANCE = Mappers.getMapper(IWalletMapper.class);

    WalletBO toWalletBO(WalletRequest walletRequest);

    WalletResponse toWalletResponse(WalletBO walletBO);
}
