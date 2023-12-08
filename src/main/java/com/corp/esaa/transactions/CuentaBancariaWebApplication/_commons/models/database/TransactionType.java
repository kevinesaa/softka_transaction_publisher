package com.corp.esaa.transactions.CuentaBancariaWebApplication._commons.models.database;

public enum TransactionType
{
    CAJERO,
    SUCURSAL,
    OTRA_CUENTA;

    @Override
    public String toString()
    {
        String sTipo = "Indefinido";

        switch (this)
        {
            case CAJERO: sTipo = "CAJERO"; break;
            case SUCURSAL: sTipo = "SUCURSAL"; break;
            case OTRA_CUENTA: sTipo = "OTRA_CUENTA"; break;
        }

        return sTipo;
    }
}
