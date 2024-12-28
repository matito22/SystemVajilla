package com.example.macdanyapp.entitys;

public class Stock {
    private Integer idStock;
    private Integer cantidadDisponible;
    private Vajilla vajilla;


    public Stock() {}

    public Stock(Integer idStock, Integer cantidadDisponible, Vajilla vajilla) {
        this.idStock = idStock;
        this.cantidadDisponible = cantidadDisponible;
        this.vajilla = vajilla;
    }

    public Stock(Integer cantidadDisponible, Vajilla vajilla) {
        this.cantidadDisponible = cantidadDisponible;
        this.vajilla = vajilla;
    }

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Vajilla getVajilla() {
        return vajilla;
    }

    public void setVajilla(Vajilla vajilla) {
        this.vajilla = vajilla;
    }


    @Override
    public String toString() {
        return "Stock{" +
                "idStock=" + idStock +
                ", cantidadDisponible=" + cantidadDisponible +
                ", vajilla=" + vajilla.getIdVajilla() +
                '}';
    }
}
