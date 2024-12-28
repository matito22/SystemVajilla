package com.example.macdanyapp.entitys;

public class DetalleAlquiler {
    private int idDetalle_Alquiler;
    private Integer cantidad;
    private Float precioUnitario;
    private Alquiler alquiler;
    private Vajilla vajilla;

    public DetalleAlquiler() {}

    public DetalleAlquiler(int idDetalle_Alquiler, Integer cantidad, Float precioUnitario, Alquiler alquiler, Vajilla vajilla) {
        this.idDetalle_Alquiler = idDetalle_Alquiler;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.alquiler = alquiler;
        this.vajilla = vajilla;
    }
    public DetalleAlquiler(Integer cantidad, Float precioUnitario, Alquiler alquiler, Vajilla vajilla) {

        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.alquiler = alquiler;
        this.vajilla = vajilla;
    }

    public DetalleAlquiler(Integer cantidad, Float precioUnitario,Vajilla vajilla) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.vajilla = vajilla;
    }




    public int getIdDetalle_Alquiler() {
        return idDetalle_Alquiler;
    }

    public void setIdDetalle_Alquiler(int idDetalle_Alquiler) {
        this.idDetalle_Alquiler = idDetalle_Alquiler;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public Vajilla getVajilla() {
        return vajilla;
    }

    public void setVajilla(Vajilla vajilla) {
        this.vajilla = vajilla;
    }

    @Override
    public String toString() {
        return "Detalle_Alquiler{" +
                "idDetalle_Alquiler=" + idDetalle_Alquiler +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", vajilla=" + vajilla.getTipoDeVajilla().getNombreTipoDeVajilla() +
                '}';
    }
}
