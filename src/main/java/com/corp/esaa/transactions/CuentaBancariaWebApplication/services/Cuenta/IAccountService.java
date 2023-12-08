package com.corp.esaa.transactions.CuentaBancariaWebApplication.services.Cuenta;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.M_Cuenta_DTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface I_Cuenta
{
    Mono<M_Cuenta_DTO> crear_Cuenta(M_Cuenta_DTO p_Cuenta);

    Flux<M_Cuenta_DTO> findAll();
}
