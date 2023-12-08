package com.corp.esaa.transactions.CuentaBancariaWebApplication.transactions.drivenAdapters.repositories;

import com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ITransactionRepository extends ReactiveMongoRepository<Transaction, String>
{
}
