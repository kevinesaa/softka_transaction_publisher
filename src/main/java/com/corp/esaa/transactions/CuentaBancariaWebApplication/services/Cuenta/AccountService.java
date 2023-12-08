package com.corp.esaa.transactions.CuentaBancariaWebApplication.services.Cuenta;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.bus.RabbitMqPublisher;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.repositorios.I_RepositorioCuentaMongo;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.M_Cliente_DTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.M_Cuenta_DTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Mongo.M_ClienteMongo;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Mongo.M_CuentaMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Sender;


@Service()
@Qualifier("MONGO")
public class Cuenta_ImpMongo implements I_Cuenta
{
    @Autowired
    I_RepositorioCuentaMongo repositorio_Cuenta;

    @Autowired
    private RabbitMqPublisher eventBus;

    @Autowired
    private Sender sender;

    @Override
    public Mono<M_Cuenta_DTO> crear_Cuenta(M_Cuenta_DTO p_Cuenta_DTO)
    {
        M_CuentaMongo cuenta = new M_CuentaMongo(p_Cuenta_DTO.getId(),
                new M_ClienteMongo(p_Cuenta_DTO.getCliente().getId(),
                        p_Cuenta_DTO.getCliente().getNombre()),
                p_Cuenta_DTO.getSaldo_Global());


        eventBus.publishMessage(cuenta);

        return repositorio_Cuenta.save(cuenta)
                .map(cuentaModel-> {
                    return new M_Cuenta_DTO(cuentaModel.getId(),
                            new M_Cliente_DTO(cuentaModel.getCliente().getId(),
                                    cuentaModel.getCliente().getNombre()),
                            cuentaModel.getSaldo_Global());
                });
    }

    @Override
    public Flux<M_Cuenta_DTO> findAll()
    {
        return repositorio_Cuenta.findAll()
                .map(cuentaModel -> new M_Cuenta_DTO(cuentaModel.getId(),
                        new M_Cliente_DTO(cuentaModel.getCliente().getId(),
                                cuentaModel.getCliente().getNombre()),
                        cuentaModel.getSaldo_Global()));
    }
}