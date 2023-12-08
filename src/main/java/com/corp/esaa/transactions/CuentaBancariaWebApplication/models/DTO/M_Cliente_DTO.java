package com.corp.esaa.transactions.CuentaBancariaWebApplication.models.DTO;


public class M_Cliente_DTO
{

    private String id;


    private String nombre;

    public M_Cliente_DTO(String id, String nombre) {
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
