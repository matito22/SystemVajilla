package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.*;
import com.example.macdanyapp.services.AlquilerService;
import com.example.macdanyapp.services.ClienteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AlquileresPendientesController implements UsuarioAwareController {

    @FXML
    public ListView<Alquiler> listViewAlquileresPendientesDiaDeHoy;
    @FXML
    public Label lblErrorFecha;
    @FXML
    public Label lblErrorHorario;
    @FXML
    public Label lblErrorDiasDeAlquiler;
    @FXML
    public Label lblErrorCostoDelivery;
    @FXML
    public Label lblErrorCamposVacios;
    @FXML
    public Label lblAlquilerModificado;
    @FXML
    public VBox vistaAlquileresPendientes;
    @FXML
    public VBox vistaModificarAlquiler;
    @FXML
    public Button buttonVolver2;
    @FXML
    public Label lblError;
    @FXML
    private ListView<Cliente> listViewClientes;
    @FXML
    private ListView<DetalleAlquiler> listViewDetalleActualizado;

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
    public TextField txtHoraComienzo;
    @FXML
    public TextField txtHoraFinalizacion;
    @FXML
    public TextField txtDiasDeAlquiler;
    @FXML
    public TextField txtCostoDelivery;
    @FXML
    public ComboBox miComboBoxEstado;
    @FXML
    public DatePicker txtFechaComienzoPicker;
    @FXML
    public DatePicker txtFechaFinalizacionPicker;
    @FXML
    public Button buttonModificar;
    @FXML
    public TextField searchFieldClientes;

    @FXML
    private Usuario usuario;
    @FXML
    private Alquiler alquiler;
    private List<Cliente> clientes;

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


    private Alquiler alquilerSeleccionado;

    private ObservableList<Alquiler> alquileresObservableList = FXCollections.observableArrayList();
    private ObservableList<Alquiler> alquileresObservableList2 = FXCollections.observableArrayList();
    private ObservableList<DetalleAlquiler> detallesObservableList = FXCollections.observableArrayList();

    private List<Alquiler> listaAlquileres=new ArrayList<Alquiler>();
    private List<Alquiler> listaAlquileres2=new ArrayList<Alquiler>();
    AlquilerService alquilerService=new AlquilerService();
    ClienteService clienteService = new ClienteService();

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
           lblError.setText("Alquiler no seleccionado");
           lblCorrecto.setVisible(true);
        }
    }
    @FXML
    public void buttonModificarAlquiler(ActionEvent event) throws IOException, SQLException {
        Alquiler alquilerSeleccionado2=null;
        //NO FUNCIONA, REVISAR 10-01
        alquilerSeleccionado2=listViewAlquileresPendientes.getSelectionModel().getSelectedItem();
        if (alquilerSeleccionado2==null){
            lblError.setText("Seleccione alquiler");
            lblError.setVisible(true);
            return;
        }

        // Ocultar la vista principal
        vistaAlquileresPendientes.setVisible(false);
        vistaAlquileresPendientes.setManaged(false);

        // Mostrar la vista de modificación
        vistaModificarAlquiler.setVisible(true);
        vistaModificarAlquiler.setManaged(true);


        clientes = clienteService.traerListaClientes();
        listViewClientes.setVisible(false);

        // Convierte la lista de clientes a ObservableList
        ObservableList<Cliente> clientesObservableList = FXCollections.observableArrayList(clientes);
        listViewClientes.setItems(clientesObservableList);

        // Define cómo se mostrará cada Cliente en el ListView
        listViewClientes.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            protected void updateItem(Cliente cliente, boolean empty) {
                super.updateItem(cliente, empty);
                if (empty || cliente == null) {
                    setText(null);
                } else {
                    setText(cliente.toString()); // Muestra el nombre y apellido
                }
            }
        });

        // Filtra los clientes basándose en la entrada del usuario
        searchFieldClientes.addEventFilter(KeyEvent.KEY_RELEASED, event2 -> {
            String filter = searchFieldClientes.getText().toLowerCase();
            if (filter.isEmpty()) {
                listViewClientes.setItems(FXCollections.observableArrayList());
                listViewClientes.setVisible(false); // Oculta si no hay filtro
            } else {
                ObservableList<Cliente> filteredList = FXCollections.observableArrayList(
                        clientes.stream()
                                .filter(cliente -> cliente.toString().toLowerCase().contains(filter))
                                .toList()
                );
                listViewClientes.setItems(filteredList);
                listViewClientes.setVisible(!filteredList.isEmpty()); // Muestra solo si hay resultados
            }
        });

        listViewClientes.setOnMouseClicked(event2 -> {
            Cliente selectedCliente = listViewClientes.getSelectionModel().getSelectedItem();
            if (selectedCliente != null) {
                searchFieldClientes.setText(selectedCliente.toString()); // Muestra el cliente seleccionado en el TextField
                listViewClientes.setVisible(false); // Oculta el ListView
            }
        });

    //MANEJAMOS LOS ESTADOS
        miComboBoxEstado.setConverter(new StringConverter<Estado>() {
            @Override
            public String toString(Estado estado) {
                return estado != null ? estado.toString() : "";
            }

            @Override
            public Estado fromString(String string) {
                return Estado.valueOf(string);  // Convierte el String de nuevo a un Estado
            }
        });

        // Rellenar txtEstado con valores del enum Estado
        ObservableList<Estado> estados = FXCollections.observableArrayList(Estado.values());
        miComboBoxEstado.setItems(estados);

        // Agregar un mensaje al cambiar el estado
        miComboBoxEstado.setOnAction(event1 -> {
            Estado seleccionado = (Estado) miComboBoxEstado.getValue();
            if (seleccionado != null) {
                System.out.println("Estado seleccionado: " + seleccionado);
            }
        });



        //DEJAMOS DE MOSTRAR LO DE ANTES Y MOSTRAMOS LO NUEVO
        alquilerSeleccionado = listViewAlquileresPendientes.getSelectionModel().getSelectedItem();




        //LLENAMOS LOS CAMPOS CON LOS VALORES DEL ALQUILER SELECCIONADO

        txtFechaComienzoPicker.setValue(alquilerSeleccionado.getFechaComienzo());
        txtFechaFinalizacionPicker.setValue(alquilerSeleccionado.getFechaFinalizacion());
        String hora = alquilerSeleccionado.getHoraComienzoTime().toString();
        txtHoraComienzo.setText(hora);
        String hora2=alquilerSeleccionado.getHoraFinalizacionTime().toString();
        txtHoraFinalizacion.setText(hora2);
        txtDiasDeAlquiler.setText(alquilerSeleccionado.getDiasAlquiler().toString());
        txtCostoDelivery.setText(alquilerSeleccionado.getCostoDelivery().toString());
        Estado estado=alquilerSeleccionado.getEstado();
        miComboBoxEstado.setValue(estado);
        searchFieldClientes.setText(alquilerSeleccionado.getCliente().toString());



    }

    @FXML
    public int buttonModificar(ActionEvent event) {
        //CREAMOS EL ALQUILER
        LocalTime horaComienzo = null;
        LocalTime horaFinalizacion = null;
        LocalDate fecha=null;
        LocalDate fecha2=null;
        Multa multa = null;
        Integer diasDeAlquiler=null;
        Float costoDelivery=null;

        fecha=txtFechaComienzoPicker.getValue();
        fecha2=txtFechaFinalizacionPicker.getValue();
        lblErrorFecha.setVisible(false);

        //ESTA VALIDACION NO FUNCIONA, SI SE INGRESA VACIO TOMA LAS FECHAS ANTERIORES
        if (fecha == null || fecha2 == null) {
            lblErrorFecha.setVisible(true);
            return 0; // O cualquier valor que consideres apropiado en caso de error
        }
        lblErrorFecha.setVisible(false);


        try {
            String hora = txtHoraComienzo.getText();
            horaComienzo = LocalTime.parse(hora);
            String hora2 = txtHoraFinalizacion.getText();
            horaFinalizacion = LocalTime.parse(hora2);
            lblErrorHorario.setVisible(false);

        } catch (DateTimeParseException e) {
            lblErrorHorario.setVisible(true);
            return 0;
        }

        try{
            diasDeAlquiler= Integer.parseInt(txtDiasDeAlquiler.getText());
            lblErrorDiasDeAlquiler.setVisible(false);
        } catch (NumberFormatException e) {
            lblErrorDiasDeAlquiler.setVisible(true);
            return 0;
        }

        try{
            costoDelivery= Float.parseFloat(txtCostoDelivery.getText());
            lblErrorCostoDelivery.setVisible(false);
        } catch (Exception e) {
            lblErrorCostoDelivery.setVisible(true);
            return 0;
        }

        Estado estado= (Estado) miComboBoxEstado.getValue();
        Cliente cliente= listViewClientes.getSelectionModel().getSelectedItem();
        if(cliente==null){
            cliente=alquilerSeleccionado.getCliente();

        }
        if(estado==null || cliente==null){
            lblErrorCamposVacios.setVisible(true);
            return 0;
        }

        try{
            alquilerService.modificarAlquilerCompleto(alquilerSeleccionado.getIdAlquiler(),fecha,fecha2,horaComienzo,horaFinalizacion,cliente.getIdCliente(),diasDeAlquiler,costoDelivery,alquilerSeleccionado.getTotalAlquiler(),estado);
            lblAlquilerModificado.setVisible(true);
        } catch (Exception e) {
            lblAlquilerModificado.setVisible(false);
            return 0;
        }
        return 1;
    }
}
