package com.corp.esaa.transactions.CuentaBancariaWebApplication.services.Transaccion;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.M_Transaccion_DTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Enum_Tipos_Deposito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface I_Transaccion
{
    Mono<M_Transaccion_DTO> Procesar_Deposito(String id_Cuenta, Enum_Tipos_Deposito tipo, BigDecimal monto);

    Flux<M_Transaccion_DTO> findAll();
}
