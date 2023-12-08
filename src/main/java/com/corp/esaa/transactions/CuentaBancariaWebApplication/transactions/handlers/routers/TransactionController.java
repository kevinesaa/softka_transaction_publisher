package com.corp.esaa.transactions.CuentaBancariaWebApplication.transactions.handlers.routers;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.TransactionDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database.TransactionType;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.transactions.useCases.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("transacciones")
public class TransactionController
{
    @Autowired
    ITransactionService transactionService;

    @GetMapping(value = "/listar_transacciones")
    public Flux<TransactionDTO> listar_transacciones()
    {
        return transactionService.findAll();
    }

    @PostMapping(value = "/Crear/Deposito/Cajero/{id_Cuenta}/{monto}")
    public Mono<TransactionDTO> Procesar_Deposito_Cajero(@PathVariable String id_Cuenta,
                                                         @PathVariable BigDecimal monto)
    {
        return transactionService.Procesar_Deposito(id_Cuenta, TransactionType.CAJERO, monto);
    }

    @PostMapping(value = "/Crear/Deposito/Sucursal/{id_Cuenta}/{monto}")
    public Mono<TransactionDTO> Procesar_Deposito_Sucursal(@PathVariable String id_Cuenta,
                                                           @PathVariable BigDecimal monto)
    {
        return transactionService.Procesar_Deposito(id_Cuenta, TransactionType.SUCURSAL, monto);
    }

    @PostMapping(value = "/Crear/Deposito/OtraCuenta/{id_Cuenta}/{monto}")
    public Mono<TransactionDTO> Procesar_Deposito_OtraCuenta(@PathVariable String id_Cuenta,
                                                             @PathVariable BigDecimal monto)
    {
        return transactionService.Procesar_Deposito(id_Cuenta, TransactionType.OTRA_CUENTA, monto);
    }

}
