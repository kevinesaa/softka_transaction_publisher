package com.corp.esaa.transactions.CuentaBancariaWebApplication.accounts.drivenAdapters.repositories;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IAccountRepository extends ReactiveMongoRepository<Account, String>
{
}
