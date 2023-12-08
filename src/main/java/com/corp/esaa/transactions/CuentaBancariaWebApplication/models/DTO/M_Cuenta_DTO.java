package com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO;



import java.math.BigDecimal;


public class M_Cuenta_DTO
{
    //@NotNull(message = "[CUENTA] [id] Campo Requerido: Id.")
    private String id;

     private M_Cliente_DTO cliente;

     //@Digits(integer = 7, fraction = 2, message = "[CUENTA] [saldo_Global] El Formato del Saldo debe ser 7 digitos enteros y 2 decimales")
    private BigDecimal saldo_Global;



    public M_Cuenta_DTO(String id, M_Cliente_DTO cliente, BigDecimal saldo_Global) {
        this.id = id;
        this.cliente = cliente;
        this.saldo_Global = saldo_Global;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public M_Cliente_DTO getCliente() {
        return cliente;
    }

    public void setCliente(M_Cliente_DTO cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getSaldo_Global() {
        return saldo_Global;
    }

    public void setSaldo_Global(BigDecimal saldo_Global) {
        this.saldo_Global = saldo_Global;
    }

    @Override
    public String toString() {
        return "M_Cuenta_DTO{" +
                "id='" + id + '\'' +
                ", cliente=" + cliente +
                ", saldo_Global=" + saldo_Global +
                '}';
    }
}
