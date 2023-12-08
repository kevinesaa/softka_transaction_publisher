package com.corp.esaa.transactions.CuentaBancariaWebApplication.transactions.useCases;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.TransactionDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database.TransactionType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface ITransactionService
{
    Mono<TransactionDTO> Procesar_Deposito(String id_Cuenta, TransactionType tipo, BigDecimal monto);

    Flux<TransactionDTO> findAll();
}
