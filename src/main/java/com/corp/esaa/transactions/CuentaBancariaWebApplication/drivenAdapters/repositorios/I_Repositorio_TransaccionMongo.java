package com.corp.esaa.transactions.CuentaBancariaWebApplication.drivenAdapters.repositorios;

import com.corp.esaa.transactions.CuentaBancariaWebApplication.models.Mongo.M_TransaccionMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface I_Repositorio_TransaccionMongo extends ReactiveMongoRepository<M_TransaccionMongo, String>
{
}
