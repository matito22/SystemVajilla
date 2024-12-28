package com.example.macdanyapp.entitys;

public class Proveedor {
    private Integer idproveedor;
    private String nombre;
    private String contacto;
    private Float costoAdquisicion;

    public Proveedor() {}

    public Proveedor(Integer idproveedor, String nombre, String contacto, Float costoAdquisicion) {
        this.idproveedor = idproveedor;
        this.nombre = nombre;
        this.contacto = contacto;
        this.costoAdquisicion = costoAdquisicion;
    }

    public Proveedor(String nombre, String contacto, Float costoAdquisicion) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.costoAdquisicion = costoAdquisicion;
    }

    public Integer getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Float getCostoAdquisicion() {
        return costoAdquisicion;
    }

    public void setCostoAdquisicion(Float costoAdquisicion) {
        this.costoAdquisicion = costoAdquisicion;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "idproveedor=" + idproveedor +
                ", nombre='" + nombre + '\'' +
                ", contacto='" + contacto + '\'' +
                ", costoAdquisicion=" + costoAdquisicion +
                '}';
    }
}
