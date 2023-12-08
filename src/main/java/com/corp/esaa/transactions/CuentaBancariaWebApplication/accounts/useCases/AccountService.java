package com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.useCases;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.drivenAdapters.bus.RabbitMqPublisher;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.drivenAdapters.repositories.IAccountRepository;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.CustomerDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.AccountDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database.Customer;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Sender;


@Service
public class AccountService implements IAccountService
{
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private RabbitMqPublisher eventBus;

    @Autowired
    private Sender sender;

    @Override
    public Mono<AccountDTO> createAccount(AccountDTO accountDTO)
    {
        Account account = new Account(accountDTO.getId(),
                new Customer(accountDTO.getCliente().getId(),
                        accountDTO.getCliente().getNombre()),
                accountDTO.getSaldo_Global());


        eventBus.publishMessage(account);

        return accountRepository.save(account)
                .map(cuentaModel-> {
                    return new AccountDTO(cuentaModel.getId(),
                            new CustomerDTO(cuentaModel.getCliente().getId(),
                                    cuentaModel.getCliente().getNombre()),
                            cuentaModel.getSaldo_Global());
                });
    }

    @Override
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