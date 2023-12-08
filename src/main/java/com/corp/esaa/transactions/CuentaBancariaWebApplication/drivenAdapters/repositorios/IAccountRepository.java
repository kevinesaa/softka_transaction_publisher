package com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.repositorios;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Mongo.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IAccountRepository extends ReactiveMongoRepository<Account, String>
{
}
