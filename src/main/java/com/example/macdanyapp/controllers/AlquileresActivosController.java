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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class AlquileresActivosController implements UsuarioAwareController  {

    @FXML
    public DatePicker txtFechaComienzoPicker;
    @FXML
    public DatePicker txtFechaFinalizacionPicker;
    @FXML
    public TextField searchFieldAlquiler;
    @FXML
    public Button buttonActualizar;
    @FXML
    public Button buttonFinalizar;
    @FXML
    public Label lblCorrecto;

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

        lblCorrecto.setVisible(false);

        // Configurar acciones para DatePicker
        txtFechaComienzoPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                actualizarListaAlquileres();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        txtFechaFinalizacionPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                actualizarListaAlquileres();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });



        listViewAlquileresActivos.setOnMouseClicked(event -> {
            Alquiler selectedAlquiler = listViewAlquileresActivos.getSelectionModel().getSelectedItem();
            if (selectedAlquiler != null) {
                searchFieldAlquiler.setText(selectedAlquiler.toString()); // Muestra el cliente seleccionado en el TextField

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

    @FXML
    public void finalizarAlquiler(ActionEvent event) throws SQLException {
        Alquiler alquiler=listViewAlquileresActivos.getSelectionModel().getSelectedItem();

        try{
            alquilerService.modificarEstadoAlquiler(Estado.FINALIZADO,alquiler.getIdAlquiler());
            lblCorrecto.setVisible(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void actualizarListaAlquileres() throws SQLException {
        // Obtén las fechas seleccionadas
        LocalDate fechaDesde = txtFechaComienzoPicker.getValue();
        LocalDate fechaHasta = txtFechaFinalizacionPicker.getValue();

        // Asegúrate de manejar valores nulos en los DatePicker
        if (fechaDesde == null || fechaHasta == null) {
            listaAlquileres = alquilerService.traerAlquilerPorEstado(Estado.ACTIVO);
        } else {
            listaAlquileres = alquilerService.traerAlquileresPorFechaYEstado(fechaDesde, fechaHasta, Estado.ACTIVO);
        }


        // Crea la lista observable
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
                    setText("Alquiler de: " + alquiler.getCliente().getNombre() + " " + alquiler.getCliente().getApellido()
                            + " || Fecha de Comienzo: " + alquiler.getFechaComienzo()
                            + " || Fecha de Finalización: " + alquiler.getFechaFinalizacion()
                            + " || Hora de comienzo: " + alquiler.getHoraComienzo()
                            + " || Hora de finalización: " + alquiler.getHoraFinalizacion());
                }
            }
        });
    }



}
