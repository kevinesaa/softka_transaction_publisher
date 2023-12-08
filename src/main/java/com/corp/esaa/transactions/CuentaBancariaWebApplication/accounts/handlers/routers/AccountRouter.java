package com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.handlers.routers;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.AccountDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.useCases.CreateAccountUseCase;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.useCases.ListAccountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("cuentas")
public class AccountRouter
{
    @Autowired
    private ListAccountUseCase listAccountUseCase;

    @Autowired
    private CreateAccountUseCase createAccountUseCase;


    @GetMapping(value = "/listar_cuentas")
    public Flux<AccountDTO> listAccounts()
    {
        return listAccountUseCase.findAll();
    }

    @PostMapping(value = "/crear")
    public Mono<AccountDTO> createAccount(@RequestBody AccountDTO requestBody)
    {
        return createAccountUseCase.createAccount(requestBody);
    }
}
