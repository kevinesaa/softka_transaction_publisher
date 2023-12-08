package com.corp.esaa.transactions.CuentaBancariaWebApplication.handlers.controllers;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.M_Cuenta_DTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.services.Cuenta.I_Cuenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("Cuentas")
public class C_Cuenta
{

    @Autowired
    @Qualifier("MONGO")
    I_Cuenta cuenta;

    @GetMapping(value = "/listar_cuentas")
    public Flux<M_Cuenta_DTO> listar_cuentas()
    {
        return cuenta.findAll();
    }

    @PostMapping(value = "/Crear")
    public Mono<M_Cuenta_DTO> Crear_Cuenta( @RequestBody M_Cuenta_DTO p_cuenta)
    {
        return cuenta.crear_Cuenta(p_cuenta);
    }
}