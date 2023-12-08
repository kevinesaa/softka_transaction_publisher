package com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.repositorios;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Mongo.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ITransactionRepository extends ReactiveMongoRepository<Transaction, String>
{
}
