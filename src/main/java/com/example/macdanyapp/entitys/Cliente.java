package com.example.macdanyapp.entitys;

public class Cliente {
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String domicilio;


    public Cliente(){};

    public Cliente(String nombre, String apellido, String telefono, String domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.domicilio = domicilio;
    }

    public Cliente(Integer idCliente,String nombre, String apellido, String telefono, String domicilio) {
        this.idCliente=idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.domicilio = domicilio;
    }




    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return nombre + " "+ apellido;
    }

}

