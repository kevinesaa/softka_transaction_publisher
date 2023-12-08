package com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("customers")
public class Customer
{
    @Id
    private String id;
    private String nombre;

    public Customer(String id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}