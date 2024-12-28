package com.example.macdanyapp.entitys;

import java.time.LocalDate;

public class Pago {
    private Integer idPago;
    private Alquiler alquiler;
    private Float monto;
    private Float montoAdelantado;
    private String tipoPago;
    private LocalDate fechaDePago;
    private LocalDate fehcaDePagoAdelantado;


    public Pago(){}

    public Pago(Alquiler alquiler, Float monto, Float montoAdelantado, String tipoPago, LocalDate fechaDePago, LocalDate fehcaDePagoAdelantado) {
        this.alquiler = alquiler;
        this.monto = monto;
        this.montoAdelantado = montoAdelantado;
        this.tipoPago = tipoPago;
        this.fechaDePago = fechaDePago;
        this.fehcaDePagoAdelantado = fehcaDePagoAdelantado;
    }

    public Pago(Integer idPago, Alquiler alquiler, Float monto, Float montoAdelantado, String tipoPago, LocalDate fechaDePago, LocalDate fehcaDePagoAdelantado) {
        this.idPago = idPago;
        this.alquiler = alquiler;
        this.monto = monto;
        this.montoAdelantado = montoAdelantado;
        this.tipoPago = tipoPago;
        this.fechaDePago = fechaDePago;
        this.fehcaDePagoAdelantado = fehcaDePagoAdelantado;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Float getMontoAdelantado() {
        return montoAdelantado;
    }

    public void setMontoAdelantado(Float montoAdelantado) {
        this.montoAdelantado = montoAdelantado;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public LocalDate getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(LocalDate fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public LocalDate getFehcaDePagoAdelantado() {
        return fehcaDePagoAdelantado;
    }

    public void setFehcaDePagoAdelantado(LocalDate fehcaDePagoAdelantado) {
        this.fehcaDePagoAdelantado = fehcaDePagoAdelantado;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", alquiler=" + alquiler +
                ", monto=" + monto +
                ", montoAdelantado=" + montoAdelantado +
                ", tipoPago='" + tipoPago + '\'' +
                ", fechaDePago=" + fechaDePago +
                ", fehcaDePagoAdelantado=" + fehcaDePagoAdelantado +
                '}';
    }
}
