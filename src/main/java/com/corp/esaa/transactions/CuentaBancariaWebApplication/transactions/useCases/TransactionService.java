package com.corp.esaa.transactions.CuentaBancariaWebApplication.transactions.useCases;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.drivenAdapters.bus.RabbitMqPublisher;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.drivenAdapters.repositories.IAccountRepository;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.transactions.drivenAdapters.repositories.ITransactionRepository;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.CustomerDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.AccountDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.TransactionDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.WrapperDTD;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database.TransactionType;
import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class TransactionService implements ITransactionService
{

    private final double COSTO_CAJERO = 2.0;

    private final double COSTO_SUCURSAL = 0.0;

    private final double COSTO_OTRO = 1.5;
    @Autowired
    ITransactionRepository transactionRepository;

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    private RabbitMqPublisher eventBus;

    @Override
    public Mono<TransactionDTO> Procesar_Deposito(String id_Cuenta, TransactionType tipo, BigDecimal monto) {

        return accountRepository.findById(id_Cuenta)
                .flatMap(cuenta -> {
                    BigDecimal costo = switch (tipo) {
                        case CAJERO -> BigDecimal.valueOf(COSTO_CAJERO);
                        case SUCURSAL -> BigDecimal.valueOf(COSTO_SUCURSAL);
                        case OTRA_CUENTA -> BigDecimal.valueOf(COSTO_OTRO);
                    };
                    BigDecimal bdSaldoActual = cuenta.getSaldo_Global();
                    BigDecimal bdSaldoNuevo = cuenta.getSaldo_Global().add(monto.subtract(costo));
                    cuenta.setSaldo_Global(bdSaldoNuevo);
                    Transaction transaccion = new Transaction(
                            cuenta,
                            monto,
                            bdSaldoActual,
                            bdSaldoNuevo,
                            costo,
                            tipo.toString()
                    );

                    return transactionRepository
                            .save(transaccion)
                            .map(transactionModel -> {
                                return new TransactionDTO(transactionModel.getId(),
                                        new AccountDTO(transactionModel.getCuenta().getId(),
                                                new CustomerDTO(transactionModel.getCuenta().getCliente().getId(),
                                                        transactionModel.getCuenta().getCliente().getNombre()
                                                ),
                                                transactionModel.getCuenta().getSaldo_Global()
                                        ), transactionModel.getMonto_transaccion(),
                                        transactionModel.getSaldo_inicial(),
                                        transactionModel.getSaldo_final(),
                                        transactionModel.getCosto_tansaccion(),
                                        transactionModel.getTipo()
                                );
                            })
                            .map(transaccionDTO -> {

                                accountRepository.save(cuenta)
                                        .flatMap(cuentaCreada -> Mono.error(new RuntimeException("Mensaje de pueba")))
                                        .onErrorResume(error -> {
                                            System.out.println("El error fue: " + error.getMessage());
                                            /**
                                             * Crear objeto de resiliencia que contenga la cuenta y la
                                             * transacción para enviar a una nueva cola de Rabbit con un
                                             * nuevo routing key.
                                             *
                                             * Crear un handler que consuma esa cola y borrar los registros ya guardados
                                             */

                                            eventBus.publishError(new WrapperDTD(transaccionDTO,transaccionDTO.getCuenta()));
                                            return Mono.empty();
                                        })
                                        .subscribe();
                                return transaccionDTO;
                            });


                });
    }

    @Override
    public Flux<TransactionDTO> findAll()
    {
        return transactionRepository.findAll()
                .map(transaccion -> {
                    return new TransactionDTO(transaccion.getId(),
                            new AccountDTO(transaccion.getCuenta().getId(),
                                    new CustomerDTO(transaccion.getCuenta().getCliente().getId(),
                                            transaccion.getCuenta().getCliente().getNombre()
                                    ),
                                    transaccion.getCuenta().getSaldo_Global()
                            ),
                            transaccion.getMonto_transaccion(),
                            transaccion.getSaldo_inicial(),
                            transaccion.getSaldo_final(),
                            transaccion.getCosto_tansaccion(),
                            transaccion.getTipo()
                    );
                });
    }
}
