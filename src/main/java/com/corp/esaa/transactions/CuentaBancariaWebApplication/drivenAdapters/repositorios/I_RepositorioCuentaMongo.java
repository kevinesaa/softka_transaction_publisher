package com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.repositorios;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Mongo.M_CuentaMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface I_RepositorioCuentaMongo extends ReactiveMongoRepository<M_CuentaMongo, String>
{
}
