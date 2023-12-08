package com.corp.esaa.transactions.CuentaBancariaWebApplication.handlers.bus;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.RabbitConfig;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.repositorios.IAccountRepository;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.repositorios.ITransactionRepository;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.AccountDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO.WrapperDTD;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;


@Component
public class RabbitMqMessageErrorConsumer  {

    @Autowired
    private Receiver receiver;

    @Autowired
    private Gson gson;

    @Autowired
    private ITransactionRepository transaccionRepository;

    @Autowired
    private IAccountRepository repositorioCuentaMongo;


    public void run(String... args) throws Exception {
        receiver.consumeAutoAck(RabbitConfig.QUEUE_NAME_ERROR)
                .map(message -> {
                    System.out.println("aqui");
                    System.out.println(message);
                    WrapperDTD wrapper = gson
                            .fromJson(new String(message.getBody()),
                                    WrapperDTD.class);

                    return wrapper;
                })
                .map(wrapperDTD -> {
                    AccountDTO cuentaDto = wrapperDTD.getCuentaDto();
                    //cuentaDto.setSaldo_Global();
                    //repositorioCuentaMongo.save()

                    return transaccionRepository.deleteById(wrapperDTD.getTransaccionDto().getId());
                })
                .subscribe((i)-> System.out.println("hola..."));
    }
}
