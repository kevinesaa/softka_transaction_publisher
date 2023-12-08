package com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO;

import java.io.Serializable;

public class WrapperDTD implements Serializable {

    private M_Transaccion_DTO transaccionDto;
    private M_Cuenta_DTO cuentaDto;

    public WrapperDTD(M_Transaccion_DTO transaccionDto, M_Cuenta_DTO cuentaDto) {
        this.transaccionDto = transaccionDto;
        this.cuentaDto = cuentaDto;
    }


    public M_Transaccion_DTO getTransaccionDto() {
        return transaccionDto;
    }

    public void setTransaccionDto(M_Transaccion_DTO transaccionDto) {
        this.transaccionDto = transaccionDto;
    }

    public M_Cuenta_DTO getCuentaDto() {
        return cuentaDto;
    }

    public void setCuentaDto(M_Cuenta_DTO cuentaDto) {
        this.cuentaDto = cuentaDto;
    }
}
