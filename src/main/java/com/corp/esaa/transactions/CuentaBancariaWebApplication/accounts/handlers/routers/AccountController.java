package com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.handlers.routers;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.AccountDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.CustomerDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.useCases.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("Cuentas")
public class AccountController
{

    @Autowired
    private IAccountService accountService;

    @GetMapping(value = "/listar_cuentas")
    public Flux<AccountDTO> listar_cuentas()
    {
        return /*Flux.fromIterable(List.of(
                new AccountDTO("a",new CustomerDTO("b","fulano"),new BigDecimal("100"))
        ))*/ accountService.findAll()
        ;
    }

    @PostMapping(value = "/Crear")
    public Mono<AccountDTO> Crear_Cuenta(@RequestBody AccountDTO p_cuenta)
    {
        return accountService.createAccount(p_cuenta);
    }
}
