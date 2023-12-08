package com.corp.esaa.transactions.CuentaBancariaWebApplication.services.Cuenta;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.AccountDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountService
{
    Mono<AccountDTO> crear_Cuenta(AccountDTO p_Cuenta);

    Flux<AccountDTO> findAll();
}
