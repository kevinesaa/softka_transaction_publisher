package com.corp.esaa.transactions.CuentaBancariaWebApplication.customers.drivenAdapters.repositories;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer,String> {
}
