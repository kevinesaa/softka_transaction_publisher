package com.corp.esaa.transactions.CuentaBancariaWebApplication.handlers.controllers;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.M_Transaccion_DTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Enum_Tipos_Deposito;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.services.Transaccion.I_Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("transacciones")
public class C_Transaccion
{
    @Autowired
    @Qualifier("MONGO")
    I_Transaccion transaccion;

    @GetMapping(value = "/listar_transacciones")
    public Flux<M_Transaccion_DTO> listar_transacciones()
    {
        return transaccion.findAll();
    }

    @PostMapping(value = "/Crear/Deposito/Cajero/{id_Cuenta}/{monto}")
    public Mono<M_Transaccion_DTO> Procesar_Deposito_Cajero(@PathVariable String id_Cuenta,
                                                            @PathVariable BigDecimal monto)
    {
        return transaccion.Procesar_Deposito(id_Cuenta, Enum_Tipos_Deposito.CAJERO, monto);
    }

    @PostMapping(value = "/Crear/Deposito/Sucursal/{id_Cuenta}/{monto}")
    public Mono<M_Transaccion_DTO> Procesar_Deposito_Sucursal(@PathVariable String id_Cuenta,
                                                        @PathVariable BigDecimal monto)
    {
        return transaccion.Procesar_Deposito(id_Cuenta, Enum_Tipos_Deposito.SUCURSAL, monto);
    }

    @PostMapping(value = "/Crear/Deposito/OtraCuenta/{id_Cuenta}/{monto}")
    public Mono<M_Transaccion_DTO> Procesar_Deposito_OtraCuenta(@PathVariable String id_Cuenta,
                                                          @PathVariable BigDecimal monto)
    {
        return transaccion.Procesar_Deposito(id_Cuenta, Enum_Tipos_Deposito.OTRA_CUENTA, monto);
    }

}
