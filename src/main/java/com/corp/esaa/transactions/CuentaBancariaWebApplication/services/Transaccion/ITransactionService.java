package com.corp.esaa.transactions.CuentaBancariaWebApplication.services.Transaccion;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.TransactionDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Mongo.TransactionType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface ITransactionService
{
    Mono<TransactionDTO> Procesar_Deposito(String id_Cuenta, TransactionType tipo, BigDecimal monto);

    Flux<TransactionDTO> findAll();
}
