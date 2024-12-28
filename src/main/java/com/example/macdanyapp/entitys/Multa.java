package com.example.macdanyapp.entitys;

import java.time.LocalDate;

import com.example.macdanyapp.entitys.Alquiler;

public class Multa {
    private Integer idMulta;
    private Float monto;
    private LocalDate fecha;
    private Alquiler alquiler;

    public Multa() {}

    public Multa(Integer idMulta, Float monto, LocalDate fecha, Alquiler alquiler) {
        this.idMulta = idMulta;
        this.monto = monto;
        this.fecha = fecha;
        this.alquiler = alquiler;
    }

    public Multa(Float monto, LocalDate fecha, Alquiler alquiler) {
        this.monto = monto;
        this.fecha = fecha;
        this.alquiler = alquiler;
    }





    public Integer getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(Integer idMulta) {
        this.idMulta = idMulta;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    @Override
    public String toString() {
        return "Multa{" +
                "idMulta=" + idMulta +
                ", monto=" + monto +
                ", fecha=" + fecha +
                ", alquiler=" + alquiler +
                '}';
    }
}
