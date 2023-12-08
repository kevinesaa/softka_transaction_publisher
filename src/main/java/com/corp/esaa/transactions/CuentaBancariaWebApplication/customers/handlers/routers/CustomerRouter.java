package com.corp.esaa.transactions.CuentaBancariaWebApplication.customers.handlers.routers;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto.CustomerDTO;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.customers.useCases.CreateCustomerUseCase;
import com.corp.esaa.transactions.CuentaBancariaWebApplication.customers.useCases.ListCustomerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customer")
public class CustomerRouter {

    @Autowired
    private ListCustomerUseCase listCustomerUseCase;

    @Autowired
    private CreateCustomerUseCase createCustomerUseCase;

    @GetMapping(value = "/all")
    public Flux<CustomerDTO> getAll()
    {
        return listCustomerUseCase.getAllCustomers();
    }

    @PostMapping(value = "/create")
    public Mono<CustomerDTO> createCustomer(CustomerDTO customerDTO){
        return createCustomerUseCase.createCustomer(customerDTO);
    }
}
