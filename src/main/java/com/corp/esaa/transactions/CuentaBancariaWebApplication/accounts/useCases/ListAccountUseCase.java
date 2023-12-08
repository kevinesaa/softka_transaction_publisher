package com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.useCases;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.AccountDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.CustomerDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.drivenAdapters.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ListAccountUseCase {

    @Autowired
    private IAccountRepository accountRepository;

    public Flux<AccountDTO> findAll()
    {
        return accountRepository.findAll()
                .map(accountModel ->
                        new AccountDTO(accountModel.getId(),
                                new CustomerDTO(accountModel.getCliente().getId(),
                                        accountModel.getCliente().getNombre()),
                                accountModel.getSaldo_Global()));
    }
}
