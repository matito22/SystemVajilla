package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.Estado;
import com.example.macdanyapp.services.AlquilerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlquileresFinalizadosController {
    @FXML
    private ListView<Alquiler> listViewAlquileresFinalizados;

    private ObservableList<Alquiler> alquileresObservableList = FXCollections.observableArrayList();


    private List<Alquiler> listaAlquileres=new ArrayList<Alquiler>();
    AlquilerService alquilerService=new AlquilerService();

    public void initialize() throws SQLException {


        listaAlquileres = alquilerService.traerAlquilerPorEstado(Estado.FINALIZADO);
        ObservableList<Alquiler> alquileresObservableList = FXCollections.observableArrayList(listaAlquileres);
        alquileresObservableList.setAll(listaAlquileres);
        listViewAlquileresFinalizados.setItems(alquileresObservableList);


        // Define cómo se mostrará cada Cliente en el ListView
        listViewAlquileresFinalizados.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            protected void updateItem(Alquiler alquiler, boolean empty) {
                super.updateItem(alquiler, empty);
                if (empty || alquiler == null) {
                    setText(null);
                } else {
                    setText("Alquiler de: "+alquiler.getCliente().getNombre()+" "+alquiler.getCliente().getApellido()+" || Fecha de Comienzo: "+alquiler.getFechaComienzo()+" || Fecha de Finalizacion: "+alquiler.getFechaFinalizacion()
                            +" || Hora de comienzo: "+alquiler.getHoraComienzo()+" || Hora de finalizacion: "+alquiler.getHoraFinalizacion()); // Muestra el nombre y apellido
                }
            }
        });


    }
}
