package com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.useCases;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.AccountDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountService
{
    Mono<AccountDTO> createAccount(AccountDTO p_Cuenta);

    Flux<AccountDTO> findAll();
}
