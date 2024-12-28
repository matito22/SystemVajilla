package com.example.macdanyapp.entitys;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class HistorialAlquileres {

    private Integer idHistorialAlquiler;
    private Alquiler alquiler;
    private Cliente cliente;
    private LocalDate fechaComienzo;
    private LocalDate fechaFinalizacion;
    private LocalTime horaComienzo;
    private LocalTime horaFinalizacion;
    private Integer diasAlquiler;
    private Float costoDelivery;
    private float totalAlquiler;
    private Multa multa;

    public HistorialAlquileres() {}

    public HistorialAlquileres(Integer idHistorialAlquiler, Alquiler alquiler, Cliente cliente, LocalDate fechaComienzo, LocalDate fechaFinalizacion, LocalTime horaComienzo, LocalTime horaFinalizacion, Integer diasAlquiler, Float costoDelivery, float totalAlquiler, Multa multa) {
        this.idHistorialAlquiler = idHistorialAlquiler;
        this.alquiler = alquiler;
        this.cliente = cliente;
        this.fechaComienzo = fechaComienzo;
        this.fechaFinalizacion = fechaFinalizacion;
        this.horaComienzo = horaComienzo;
        this.horaFinalizacion = horaFinalizacion;
        this.diasAlquiler = diasAlquiler;
        this.costoDelivery = costoDelivery;
        this.totalAlquiler = totalAlquiler;
        this.multa = multa;
    }

    public HistorialAlquileres(Alquiler alquiler, Cliente cliente, LocalDate fechaComienzo, LocalDate fechaFinalizacion, LocalTime horaComienzo, LocalTime horaFinalizacion, Integer diasAlquiler, Float costoDelivery, float totalAlquiler, Multa multa) {
        this.alquiler = alquiler;
        this.cliente = cliente;
        this.fechaComienzo = fechaComienzo;
        this.fechaFinalizacion = fechaFinalizacion;
        this.horaComienzo = horaComienzo;
        this.horaFinalizacion = horaFinalizacion;
        this.diasAlquiler = diasAlquiler;
        this.costoDelivery = costoDelivery;
        this.totalAlquiler = totalAlquiler;
        this.multa = multa;
    }

    public Integer getIdHistorialAlquiler() {
        return idHistorialAlquiler;
    }

    public void setIdHistorialAlquiler(Integer idHistorialAlquiler) {
        this.idHistorialAlquiler = idHistorialAlquiler;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(LocalDate fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public LocalTime getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(LocalTime horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public LocalTime getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public void setHoraFinalizacion(LocalTime horaFinalizacion) {
        this.horaFinalizacion = horaFinalizacion;
    }

    public Integer getDiasAlquiler() {
        return diasAlquiler;
    }

    public void setDiasAlquiler(Integer diasAlquiler) {
        this.diasAlquiler = diasAlquiler;
    }

    public Float getCostoDelivery() {
        return costoDelivery;
    }

    public void setCostoDelivery(Float costoDelivery) {
        this.costoDelivery = costoDelivery;
    }

    public float getTotalAlquiler() {
        return totalAlquiler;
    }

    public void setTotalAlquiler(float totalAlquiler) {
        this.totalAlquiler = totalAlquiler;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    @Override
    public String toString() {
        return "HistorialAlquileres{" +
                "idHistorialAlquiler=" + idHistorialAlquiler +
                ", alquiler=" + alquiler.getIdAlquiler() +
                ", cliente=" + cliente.getIdCliente() +
                ", fechaComienzo=" + fechaComienzo +
                ", fechaFinalizacion=" + fechaFinalizacion +
                ", horaComienzo=" + horaComienzo +
                ", horaFinalizacion=" + horaFinalizacion +
                ", diasAlquiler=" + diasAlquiler +
                ", costoDelivery=" + costoDelivery +
                ", totalAlquiler=" + totalAlquiler +
                ", multa=" + multa +
                '}';
    }
}
