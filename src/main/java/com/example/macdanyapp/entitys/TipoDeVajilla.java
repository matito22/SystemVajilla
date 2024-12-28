package com.example.macdanyapp.entitys;

public class TipoDeVajilla {
    private int idTipoDeVajilla;
    private String nombreTipoDeVajilla;


    public TipoDeVajilla() {}

    public TipoDeVajilla(String nombreTipoDeVajilla) {
        this.nombreTipoDeVajilla = nombreTipoDeVajilla;
    }

    public TipoDeVajilla(int idTipoDeVajilla,String nombreTipoDeVajilla) {
       this.idTipoDeVajilla = idTipoDeVajilla;
        this.nombreTipoDeVajilla = nombreTipoDeVajilla;
    }


    public int getIdTipoDeVajilla() {
        return idTipoDeVajilla;
    }

    public void setIdTipoDeVajilla(int idTipoDeVajilla) {
        this.idTipoDeVajilla = idTipoDeVajilla;
    }

    public String getNombreTipoDeVajilla() {
        return nombreTipoDeVajilla;
    }

    public void setNombreTipoDeVajilla(String nombreTipoDeVajilla) {
        this.nombreTipoDeVajilla = nombreTipoDeVajilla;
    }

    @Override
    public String toString() {
        return "TipoDeVajilla{" +
                "idTipoDeVajilla=" + idTipoDeVajilla +
                ", nombreTipoDeVajilla='" + nombreTipoDeVajilla + '\'' +
                '}';
    }
}
