package com.corp.esaa.transactions.CuentaBancariaWebApplication.services.Cuenta;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.bus.RabbitMqPublisher;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.repositorios.IAccountRepository;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.CustomerDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.AccountDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Mongo.Customer;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Mongo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Sender;


@Service
public class AccountService implements IAccountService
{
    @Autowired
    IAccountRepository repositorio_Cuenta;

    @Autowired
    private RabbitMqPublisher eventBus;

    @Autowired
    private Sender sender;

    @Override
    public Mono<AccountDTO> crear_Cuenta(AccountDTO p_Cuenta_DTO)
    {
        Account cuenta = new Account(p_Cuenta_DTO.getId(),
                new Customer(p_Cuenta_DTO.getCliente().getId(),
                        p_Cuenta_DTO.getCliente().getNombre()),
                p_Cuenta_DTO.getSaldo_Global());


        eventBus.publishMessage(cuenta);

        return repositorio_Cuenta.save(cuenta)
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
        return repositorio_Cuenta.findAll()
                .map(cuentaModel -> new AccountDTO(cuentaModel.getId(),
                        new CustomerDTO(cuentaModel.getCliente().getId(),
                                cuentaModel.getCliente().getNombre()),
                        cuentaModel.getSaldo_Global()));
    }
}