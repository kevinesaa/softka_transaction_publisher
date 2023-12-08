package com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("accounts")
public class Account
{
    @Id
    private String id;
    private BigDecimal saldo_Global;
    private Customer cliente;

    public Account(String id, Customer cliente, BigDecimal saldo_Global) {
        this.id = id;
        this.saldo_Global = saldo_Global;
        this.cliente = cliente;
    }

    public Account()
    {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getSaldo_Global() {
        return saldo_Global;
    }

    public void setSaldo_Global(BigDecimal saldo_Global) {
        this.saldo_Global = saldo_Global;
    }

    public Customer getCliente() {
        return cliente;
    }

    public void setCliente(Customer cliente) {
        this.cliente = cliente;
    }
}
