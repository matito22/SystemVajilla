package com.example.macdanyapp.entitys;

import java.time.LocalDate;

public class InventarioMovimientos {
    private int idInventario;
    private Vajilla vajilla;
    private int cantidad;
    private TipoMovimiento tipoMovimiento;
    private LocalDate fechaDeMovimiento;



    public InventarioMovimientos() {}

    public InventarioMovimientos(int idInventario, Vajilla vajilla, int cantidad, TipoMovimiento tipoMovimiento, LocalDate fechaDeMovimiento) {
        this.idInventario = idInventario;
        this.vajilla = vajilla;
        this.cantidad = cantidad;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaDeMovimiento = fechaDeMovimiento;
    }

    public InventarioMovimientos(Vajilla vajilla, int cantidad, TipoMovimiento tipoMovimiento, LocalDate fechaDeMovimiento) {
        this.vajilla = vajilla;
        this.cantidad = cantidad;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaDeMovimiento = fechaDeMovimiento;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public Vajilla getVajilla() {
        return vajilla;
    }

    public void setVajilla(Vajilla vajilla) {
        this.vajilla = vajilla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public TipoMovimiento getTipoDeMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoDeMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public LocalDate getFechaDeMovimiento() {
        return fechaDeMovimiento;
    }

    public void setFechaDeMovimiento(LocalDate fechaDeMovimiento) {
        this.fechaDeMovimiento = fechaDeMovimiento;
    }

    @Override
    public String toString() {
        return "InventarioMovimientos{" +
                "idInventario=" + idInventario +
                ", vajilla=" + vajilla.getIdVajilla() +
                ", cantidad=" + cantidad +
                ", tipoDeMovimiento=" + tipoMovimiento +
                ", fechaDeMovimiento=" + fechaDeMovimiento +
                '}';
    }
}
