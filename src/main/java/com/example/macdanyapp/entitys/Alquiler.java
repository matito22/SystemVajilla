package com.example.macdanyapp.entitys;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Alquiler {
    private Integer idAlquiler;
    private LocalDate fechaComienzo;
    private LocalDate fechaFinalizacion;
    private LocalTime horaComienzo;
    private LocalTime horaFinalizacion;
    private Cliente cliente;
    private Integer diasAlquiler;
    private Float costoDelivery;
    private float totalAlquiler;
    private Estado estado;
    private List<DetalleAlquiler> listaVajillaAlquilada;
    private Multa multa;
    private String etiqueta;

    private static final DateTimeFormatter HORA_FORMATO = DateTimeFormatter.ofPattern("HH:mm");

    public Alquiler() {}

    public Alquiler(Integer idAlquiler, LocalDate fechaComienzo, LocalDate fechaFinalizacion, LocalTime horaComienzo, LocalTime horaFinalizacion, Cliente cliente, Integer diasAlquiler, Float costoDelivery, float totalAlquiler, Estado estado, List<DetalleAlquiler> listaVajillaAlquilada, Multa multa, String etiqueta) {
        this.idAlquiler = idAlquiler;
        this.fechaComienzo = fechaComienzo;
        this.fechaFinalizacion = fechaFinalizacion;
        this.horaComienzo = validateAndFormatTime(horaComienzo);
        this.horaFinalizacion = validateAndFormatTime(horaFinalizacion);
        this.cliente = cliente;
        this.diasAlquiler = diasAlquiler;
        this.costoDelivery = costoDelivery;
        this.totalAlquiler = totalAlquiler;
        this.estado = estado;
        this.listaVajillaAlquilada = listaVajillaAlquilada;
        this.multa = multa;
        this.etiqueta = etiqueta;
    }

    public Alquiler(int idAlquiler, LocalDate fechaComienzo, LocalDate fechaFinalizacion,
                    LocalTime horaComienzo, LocalTime horaFinalizacion, int diasDeAlquiler,
                    float costoDelivery, float totalAlquiler, Estado estado, Multa multa,
                    Cliente cliente) {
        this.idAlquiler = idAlquiler;
        this.fechaComienzo = fechaComienzo;
        this.fechaFinalizacion = fechaFinalizacion;
        this.horaComienzo = validateAndFormatTime(horaComienzo);
        this.horaFinalizacion = validateAndFormatTime(horaFinalizacion);
        this.diasAlquiler = diasDeAlquiler;
        this.costoDelivery = costoDelivery;
        this.totalAlquiler = totalAlquiler;
        this.estado = estado;
        this.multa = multa;
        this.cliente = cliente;
    }

    public Alquiler(LocalDate fechaComienzo, LocalDate fechaFinalizacion, LocalTime horaComienzo, LocalTime horaFinalizacion, Cliente cliente, Integer diasAlquiler, Float costoDelivery, float totalAlquiler, Estado estado, List<DetalleAlquiler> listaVajillaAlquilada, Multa multa, String etiqueta) {
        this.fechaComienzo = fechaComienzo;
        this.fechaFinalizacion = fechaFinalizacion;
        this.horaComienzo = validateAndFormatTime(horaComienzo);
        this.horaFinalizacion = validateAndFormatTime(horaFinalizacion);
        this.cliente = cliente;
        this.diasAlquiler = diasAlquiler;
        this.costoDelivery = costoDelivery;
        this.totalAlquiler = totalAlquiler;
        this.estado = estado;
        this.listaVajillaAlquilada = listaVajillaAlquilada;
        this.multa = multa;
        this.etiqueta = etiqueta;
    }

    public List<DetalleAlquiler> getListaVajillaAlquilada() {
        return listaVajillaAlquilada;
    }

    public void setListaVajillaAlquilada(List<DetalleAlquiler> listaVajillaAlquilada) {
        this.listaVajillaAlquilada = listaVajillaAlquilada;
    }

    public Integer getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(Integer idAlquiler) {
        this.idAlquiler = idAlquiler;
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

    public String getHoraComienzo() {
        return horaComienzo.format(HORA_FORMATO);
    }

    public void setHoraComienzo(String hora) {
        this.horaComienzo = LocalTime.parse(hora,HORA_FORMATO);
    }

    public String getHoraFinalizacion() {
        return horaFinalizacion.format(HORA_FORMATO);
    }

    public void setHoraFinalizacion(String hora) {
        this.horaFinalizacion = LocalTime.parse(hora,HORA_FORMATO);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }



    public Multa getMulta() {
        return multa;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public LocalTime getHoraComienzoTime() {
        return horaComienzo;
    }

    public void setHoraComienzo(LocalTime horaComienzo) {
        this.horaComienzo = horaComienzo;
    }


    public LocalTime getHoraFinalizacionTime() {
        return horaFinalizacion;
    }

    public void setHoraFinalizacion(LocalTime horaFinalizacion) {
        this.horaFinalizacion = horaFinalizacion;
    }



    @Override
    public String toString() {
        return "Alquiler{" +
                "idAlquiler=" + idAlquiler +
                ", fechaComienzo=" + fechaComienzo +
                ", fechaFinalizacion=" + fechaFinalizacion +
                ", horaComienzo=" + horaComienzo +
                ", horaFinalizacion=" + horaFinalizacion +
                ", cliente=" + cliente +
                ", diasAlquiler=" + diasAlquiler +
                ", costoDelivery=" + costoDelivery +
                ", totalAlquiler=" + totalAlquiler +
                ", estado=" + estado +
                ", listaVajilla=" + listaVajillaAlquilada +
                ", multa=" + multa +
                ", etiqueta='" + etiqueta + '\'' +
                '}';
    }

    private LocalTime validateAndFormatTime(LocalTime time) {
        // Formatea la hora para ajustarla a "HH:mm" eliminando los segundos
        String formattedTime = time.format(HORA_FORMATO);
        return LocalTime.parse(formattedTime, HORA_FORMATO);
    }
}
