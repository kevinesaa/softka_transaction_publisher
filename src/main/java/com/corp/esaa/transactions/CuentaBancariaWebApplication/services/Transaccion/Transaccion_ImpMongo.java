package com.corp.esaa.transactions.CuentaBancariaWebApplication.services.Transaccion;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.bus.RabbitMqPublisher;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.repositorios.I_RepositorioCuentaMongo;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.repositorios.I_Repositorio_TransaccionMongo;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.M_Cliente_DTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.M_Cuenta_DTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.M_Transaccion_DTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.WrapperDTD;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Enum_Tipos_Deposito;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Mongo.M_TransaccionMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@Qualifier("MONGO")
public class Transaccion_ImpMongo implements I_Transaccion
{

    private final double COSTO_CAJERO = 2.0;

    private final double COSTO_SUCURSAL = 0.0;

    private final double COSTO_OTRO = 1.5;
    @Autowired
    I_Repositorio_TransaccionMongo transaccion_repositorio;

    @Autowired
    I_RepositorioCuentaMongo cuenta_repositorio;

    @Autowired
    private RabbitMqPublisher eventBus;

    @Override
    public Mono<M_Transaccion_DTO> Procesar_Deposito(String id_Cuenta, Enum_Tipos_Deposito tipo, BigDecimal monto) {

        return cuenta_repositorio.findById(id_Cuenta)
                .flatMap(cuenta -> {
                    BigDecimal costo = switch (tipo) {
                        case CAJERO -> BigDecimal.valueOf(COSTO_CAJERO);
                        case SUCURSAL -> BigDecimal.valueOf(COSTO_SUCURSAL);
                        case OTRA_CUENTA -> BigDecimal.valueOf(COSTO_OTRO);
                    };
                    BigDecimal bdSaldoActual = cuenta.getSaldo_Global();
                    BigDecimal bdSaldoNuevo = cuenta.getSaldo_Global().add(monto.subtract(costo));
                    cuenta.setSaldo_Global(bdSaldoNuevo);
                    M_TransaccionMongo transaccion = new M_TransaccionMongo(
                            cuenta,
                            monto,
                            bdSaldoActual,
                            bdSaldoNuevo,
                            costo,
                            tipo.toString()
                    );

                    return transaccion_repositorio
                            .save(transaccion)
                            .map(transactionModel -> {
                                return new M_Transaccion_DTO(transactionModel.getId(),
                                        new M_Cuenta_DTO(transactionModel.getCuenta().getId(),
                                                new M_Cliente_DTO(transactionModel.getCuenta().getCliente().getId(),
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

                                cuenta_repositorio.save(cuenta)
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
    public Flux<M_Transaccion_DTO> findAll()
    {
        return transaccion_repositorio.findAll()
                .map(transaccion -> {
                    return new M_Transaccion_DTO(transaccion.getId(),
                            new M_Cuenta_DTO(transaccion.getCuenta().getId(),
                                    new M_Cliente_DTO(transaccion.getCuenta().getCliente().getId(),
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
