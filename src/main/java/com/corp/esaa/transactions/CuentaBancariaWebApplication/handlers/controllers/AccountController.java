package com.corp.esaa.transactions.CuentaBancariaWebApplication.handlers.controllers;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.AccountDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.services.Cuenta.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("Cuentas")
public class AccountController
{

    @Autowired
    @Qualifier("MONGO")
    IAccountService caccountServiceenta;

    @GetMapping(value = "/listar_cuentas")
    public Flux<AccountDTO> listar_cuentas()
    {
        return caccountServiceenta.findAll();
    }

    @PostMapping(value = "/Crear")
    public Mono<AccountDTO> Crear_Cuenta(@RequestBody AccountDTO p_cuenta)
    {
        return caccountServiceenta.crear_Cuenta(p_cuenta);
    }
}
