package com.corp.esaa.transactions.CuentaBancariaWebApplication.customers.useCases;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.CustomerDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.customers.drivenAdapters.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
public class ListCustomerUseCase {

    @Autowired
    private CustomerRepository customerRepository;

    public Flux<CustomerDTO> getAllCustomers() {

        return Flux.fromIterable(List.of(new CustomerDTO("a","ka")));
    }
}
