package com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.useCases;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database.Account;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database.Customer;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.AccountDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.CustomerDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.drivenAdapters.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateAccountUseCase {

    @Autowired
    private IAccountRepository accountRepository;

    public Mono<AccountDTO> createAccount(AccountDTO accountDTO)
    {
        Account account = new Account(accountDTO.getId(),
                new Customer(accountDTO.getCliente().getId(),
                        accountDTO.getCliente().getNombre()),
                accountDTO.getSaldo_Global());


        //eventBus.publishMessage(account);

        return accountRepository.save(account)
                .map(cuentaModel-> {
                    return new AccountDTO(cuentaModel.getId(),
                            new CustomerDTO(cuentaModel.getCliente().getId(),
                                    cuentaModel.getCliente().getNombre()),
                            cuentaModel.getSaldo_Global());
                });
    }
}
