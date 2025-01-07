package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.Alquiler;
import com.example.macdanyapp.entitys.Estado;
import com.example.macdanyapp.entitys.Usuario;
import com.example.macdanyapp.entitys.UsuarioAwareController;
import com.example.macdanyapp.services.AlquilerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlquileresPendientesController implements UsuarioAwareController {

    @FXML
    public ListView<Alquiler> listViewAlquileresPendientesDiaDeHoy;

    @FXML
    public Button buttonActivarAlquiler;
    @FXML
    public Label lblCorrecto;
    @FXML
    public TextField searchFieldAlquilerPendientes;
    @FXML
    public TextField searchFieldAlquilerPendientesHoy;
    @FXML
    public Button buttonModificarAlquiler;
    @FXML
    private Usuario usuario;
    @FXML
    private Alquiler alquiler;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public Alquiler getAlquiler() {
        return alquiler;
    }
    @FXML
    public Button buttonVolver;
    @FXML
    private ListView<Alquiler> listViewAlquileresPendientes;

    private ObservableList<Alquiler> alquileresObservableList = FXCollections.observableArrayList();
    private ObservableList<Alquiler> alquileresObservableList2 = FXCollections.observableArrayList();

    private List<Alquiler> listaAlquileres=new ArrayList<Alquiler>();
    private List<Alquiler> listaAlquileres2=new ArrayList<Alquiler>();
    AlquilerService alquilerService=new AlquilerService();

    public void initialize() throws SQLException {
        lblCorrecto.setVisible(false);
        //LISTA ALQUILERES PENDIENTES
        listaAlquileres = alquilerService.traerAlquilerPorEstado(Estado.PENDIENTE);
        ObservableList<Alquiler> alquileresObservableList = FXCollections.observableArrayList(listaAlquileres);
        alquileresObservableList.setAll(listaAlquileres);
        listViewAlquileresPendientes.setItems(alquileresObservableList);


        listViewAlquileresPendientes.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
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


        listViewAlquileresPendientes.setOnMouseClicked(event -> {
            Alquiler selectedAlquiler = listViewAlquileresPendientes.getSelectionModel().getSelectedItem();
            if (selectedAlquiler != null) {
                searchFieldAlquilerPendientes.setText(selectedAlquiler.toString()); // Muestra el cliente seleccionado en el TextField

            }
        });


        //LISTA ALQUILERES PENDIENTES DIA DE HOY

        listaAlquileres2 = alquilerService.traerAlquilerYActivarlo(Estado.PENDIENTE, LocalDate.now());
        ObservableList<Alquiler> alquileresObservableList2 = FXCollections.observableArrayList(listaAlquileres2);
        alquileresObservableList2.setAll(listaAlquileres2);
        listViewAlquileresPendientesDiaDeHoy.setItems(alquileresObservableList2);


        listViewAlquileresPendientesDiaDeHoy.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
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

        listViewAlquileresPendientesDiaDeHoy.setOnMouseClicked(event -> {
            Alquiler selectedAlquiler = listViewAlquileresPendientesDiaDeHoy.getSelectionModel().getSelectedItem();
            if (selectedAlquiler != null) {
                searchFieldAlquilerPendientesHoy.setText(selectedAlquiler.toString()); // Muestra el cliente seleccionado en el TextField

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

    //MODIFICO EL ALQUILER DE PENDIENTE
    @FXML
    public void buttonActivarAlquiler(ActionEvent event) {
        Alquiler alquiler=listViewAlquileresPendientesDiaDeHoy.getSelectionModel().getSelectedItem();

        try{
            alquilerService.modificarEstadoAlquiler(Estado.ACTIVO,alquiler.getIdAlquiler());
            lblCorrecto.setVisible(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void buttonModificarAlquiler(ActionEvent event) throws IOException {
        Alquiler alquiler = listViewAlquileresPendientes.getSelectionModel().getSelectedItem();
        listViewAlquileresPendientes.setVisible(false);
        listViewAlquileresPendientesDiaDeHoy.setVisible(false);


    }
}
