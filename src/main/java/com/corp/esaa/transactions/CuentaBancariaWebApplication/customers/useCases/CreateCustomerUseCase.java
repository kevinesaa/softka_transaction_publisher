package com.corp.esaa.transactions.CuentaBancariaWebApplication.customers.useCases;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.CustomerDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.customers.drivenAdapters.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateCustomerUseCase {

    @Autowired
    private CustomerRepository customerRepository;

    public Mono<CustomerDTO> createCustomer(CustomerDTO customerDTO){

        return Mono.empty();
    }
}
