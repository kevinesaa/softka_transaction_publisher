package com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO;

import java.io.Serializable;

public class WrapperDTD implements Serializable {

    private TransactionDTO transaccionDto;
    private AccountDTO cuentaDto;

    public WrapperDTD(TransactionDTO transaccionDto, AccountDTO cuentaDto) {
        this.transaccionDto = transaccionDto;
        this.cuentaDto = cuentaDto;
    }


    public TransactionDTO getTransaccionDto() {
        return transaccionDto;
    }

    public void setTransaccionDto(TransactionDTO transaccionDto) {
        this.transaccionDto = transaccionDto;
    }

    public AccountDTO getCuentaDto() {
        return cuentaDto;
    }

    public void setCuentaDto(AccountDTO cuentaDto) {
        this.cuentaDto = cuentaDto;
    }
}
