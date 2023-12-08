package com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.dto;



import java.math.BigDecimal;


public class TransactionDTO
{
    private String id;
    private AccountDTO cuenta;
    private BigDecimal monto_transaccion;
    private BigDecimal saldo_inicial;
    private BigDecimal saldo_final;
    private BigDecimal costo_tansaccion;
    private String tipo;

    public TransactionDTO(String id, AccountDTO cuenta, BigDecimal monto_transaccion, BigDecimal saldo_inicial, BigDecimal saldo_final, BigDecimal costo_tansaccion, String tipo) {
        this.id = id;
        this.cuenta = cuenta;
        this.monto_transaccion = monto_transaccion;
        this.saldo_inicial = saldo_inicial;
        this.saldo_final = saldo_final;
        this.costo_tansaccion = costo_tansaccion;
        this.tipo = tipo;
    }

    public AccountDTO getCuenta() {
        return cuenta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCuenta(AccountDTO cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getMonto_transaccion() {
        return monto_transaccion;
    }

    public void setMonto_transaccion(BigDecimal monto_transaccion) {
        this.monto_transaccion = monto_transaccion;
    }

    public BigDecimal getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(BigDecimal saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public BigDecimal getSaldo_final() {
        return saldo_final;
    }

    public void setSaldo_final(BigDecimal saldo_final) {
        this.saldo_final = saldo_final;
    }

    public BigDecimal getCosto_tansaccion() {
        return costo_tansaccion;
    }

    public void setCosto_tansaccion(BigDecimal costo_tansaccion) {
        this.costo_tansaccion = costo_tansaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
