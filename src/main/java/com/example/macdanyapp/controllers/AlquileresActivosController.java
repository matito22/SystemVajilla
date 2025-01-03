package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.*;
import com.example.macdanyapp.services.AlquilerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class AlquileresActivosController implements UsuarioAwareController  {

    @FXML
    private Usuario usuario;
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    @FXML
    public Button buttonVolver;
    @FXML
    private ListView<Alquiler> listViewAlquileresActivos;
    @FXML
    private ListView<Alquiler> listViewAlquileresPendientes;

    private ObservableList<Alquiler> alquileresObservableList = FXCollections.observableArrayList();
    private ObservableList<Alquiler> alquileresObservableListPendientes = FXCollections.observableArrayList();



    private List<Alquiler> listaAlquileres=new ArrayList<Alquiler>();
    AlquilerService alquilerService=new AlquilerService();

    public void initialize() throws SQLException {


        listaAlquileres = alquilerService.traerAlquilerPorEstado(Estado.ACTIVO);
        ObservableList<Alquiler> alquileresObservableList = FXCollections.observableArrayList(listaAlquileres);
        alquileresObservableList.setAll(listaAlquileres);
        listViewAlquileresActivos.setItems(alquileresObservableList);


        // Define cómo se mostrará cada Cliente en el ListView
        listViewAlquileresActivos.setCellFactory(param -> new ListCell<>() {
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
    @FXML
    public void buttonVolver(ActionEvent event) throws IOException {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/macdanyapp/template/TablaOpciones.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la nueva escena
        Object controller = loader.getController();
        if (controller instanceof UsuarioAwareController) {
            // Pasar el usuario al nuevo controlador
            ((UsuarioAwareController) controller).setUsuario(usuario);
        }

        // Cambiar de escena
        Scene scene = new Scene(root, 600, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }



}
