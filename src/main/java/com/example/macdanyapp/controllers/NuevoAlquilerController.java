package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.*;
import com.example.macdanyapp.services.*;
import javafx.animation.PauseTransition;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NuevoAlquilerController implements UsuarioAwareController {

    @FXML
    public Button buttonVolver;
    @FXML
    private Usuario usuario;
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    @FXML
    public TextField txtCantidad;
    @FXML
    public TextField txtPrecioUnitario;
    @FXML
    public Button buttonDetalleAlquilerFinalizado;
    @FXML
    private DatePicker txtFechaComienzoPicker;
    @FXML
    private DatePicker txtFechaFinalizacionPicker;
    @FXML
    private TextField horaComienzoPicker;
    @FXML
    private TextField horaFinalizacionPicker;
    @FXML
    private TextField txtDiasDeAlquiler;
    @FXML
    private TextField txtCostoDelivery;
    @FXML
    ComboBox<Estado> miComboBoxEstado = new ComboBox<>();

    @FXML
    private ListView<Cliente> listViewClientes;
    @FXML
    private TextField searchFieldClientes;
    @FXML
    private Label lblErrorHorario;
    @FXML
    private Label lblErrorDiasDeAlquiler;
    @FXML
    private Label lblErrorCamposVacios;
    @FXML
    private Label lblErrorCostoDelivery;
    @FXML
    private TextField txtCantidadVajilla;

    @FXML
    private TextField txtPrecioUnitarioVajilla;

    @FXML
    private Label lblErrorTipoDeVajilla;
    @FXML
    private Label lblVajillaAgregada;
    @FXML
    private Label lblErrorStockInsuficiente;

    @FXML
    private ListView<TipoDeVajilla> listViewTiposDeVajilla;
    @FXML
    private ListView<DetalleAlquiler> listViewDetalleActualizado;
    @FXML
    private TextField searchFieldTipoDeVajilla;
    @FXML
    private Button buttonAgregarDetalleAlquiler;
    @FXML
    private Button buttonCrearAlquiler;
    @FXML
    private Label lblErrorFecha;

    private int idGenerado;
    float totalAlquiler=0;

    private List<TipoDeVajilla> tipoDeVajillass= new ArrayList<>();
    TipoDeVajillaService tipoDeVajillassService = new TipoDeVajillaService();
    private List<DetalleAlquiler> listaDetalleAlquiler= new ArrayList<>();
    private List<DetalleAlquiler> listaDetalleAlquilerModificada= new ArrayList<>();
    private List<DetalleAlquiler> listaDetalleVajilla= new ArrayList<>();
    private List<Cliente> clientes;
    private ObservableList<DetalleAlquiler> detallesObservableList = FXCollections.observableArrayList();
    MultaService multaService = new MultaService();
    ClienteService clienteService = new ClienteService();
    AlquilerService alquilerService = new AlquilerService();
    VajillaService vajillaService=new VajillaService();
    DetalleAlquilerService detalleAlquilerService=new DetalleAlquilerService();
    StockService stockService=new StockService();

    public void initialize() throws InstantiationException, IllegalAccessException, SQLException {
        buttonAgregarDetalleAlquiler.setDisable(true);
        txtCantidadVajilla.setDisable(true);
        txtPrecioUnitarioVajilla.setDisable(true);
        listViewTiposDeVajilla.setDisable(true);
        listViewDetalleActualizado.setDisable(true);
        searchFieldTipoDeVajilla.setDisable(true);
        buttonDetalleAlquilerFinalizado.setDisable(true);

        //CARGAR Y MANEJAR LISTVIEW CLIENTES
        clientes = clienteService.traerListaClientes();

        // Convierte la lista de clientes a ObservableList
        ObservableList<Cliente> clientesObservableList = FXCollections.observableArrayList(clientes);
        detallesObservableList = FXCollections.observableArrayList();
        listViewDetalleActualizado.setItems(detallesObservableList);

        // Crea un ListView para mostrar los resultados filtrados
        listViewClientes.setVisible(false); // Oculta inicialmente el ListView
        lblErrorTipoDeVajilla.setVisible(false);
        listViewDetalleActualizado.setVisible(true);
        lblErrorStockInsuficiente.setVisible(false);
        lblVajillaAgregada.setVisible(false);

        // Carga inicial de todos los clientes en el ListView
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
        searchFieldClientes.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
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


        //CARGAR Y MANEJAR LISTVIEW TIPOS DE VAJILLA
        tipoDeVajillass = tipoDeVajillassService.traerListaTipoDeVajilla();

        // Convierte la lista de clientes a ObservableList
        ObservableList<TipoDeVajilla> tipoDeVajillaObservableList = FXCollections.observableArrayList(tipoDeVajillass);



        // Crea un ListView para mostrar los resultados filtrados
        listViewTiposDeVajilla.setVisible(false); // Oculta inicialmente el ListView




        // Define cómo se mostrará cada Cliente en el ListView
        listViewTiposDeVajilla.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            protected void updateItem(TipoDeVajilla tipoDeVajilla, boolean empty) {
                super.updateItem(tipoDeVajilla, empty);
                if (empty || tipoDeVajilla == null) {
                    setText(null);
                } else {
                    setText(tipoDeVajilla.getNombreTipoDeVajilla()); // Muestra el nombre y apellido
                }
            }
        });


        searchFieldTipoDeVajilla.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            String filter = searchFieldTipoDeVajilla.getText().toLowerCase();
            if (filter.isEmpty()) {
                listViewTiposDeVajilla.setItems(FXCollections.observableArrayList());
                listViewTiposDeVajilla.setVisible(false); // Oculta si no hay filtro
            } else {
                ObservableList<TipoDeVajilla> filteredList = FXCollections.observableArrayList(
                        tipoDeVajillass.stream()
                                .filter(tipoDeVajilla -> tipoDeVajilla.toString().toLowerCase().contains(filter))
                                .toList()
                );
                listViewTiposDeVajilla.setItems(filteredList);
                listViewTiposDeVajilla.setVisible(!filteredList.isEmpty()); // Muestra solo si hay resultados
            }
        });


        listViewTiposDeVajilla.setOnMouseClicked(event -> {
            TipoDeVajilla selectedTipoDeVajilla = listViewTiposDeVajilla.getSelectionModel().getSelectedItem();
            if (selectedTipoDeVajilla != null) {
                searchFieldTipoDeVajilla.setText(selectedTipoDeVajilla.toString()); // Muestra el cliente seleccionado en el TextField
                listViewTiposDeVajilla.setVisible(false); // Oculta el ListView
            }
        });




        listViewClientes.setOnMouseClicked(event -> {
            Cliente selectedCliente = listViewClientes.getSelectionModel().getSelectedItem();
            if (selectedCliente != null) {
                searchFieldClientes.setText(selectedCliente.toString()); // Muestra el cliente seleccionado en el TextField
                listViewClientes.setVisible(false); // Oculta el ListView
            }
        });



        //CARGAR Y MANEJAR COMBOBOX DE ESTADOS
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
            miComboBoxEstado.setOnAction(event -> {
                Estado seleccionado = miComboBoxEstado.getValue();
                if (seleccionado != null) {
                    System.out.println("Estado seleccionado: " + seleccionado);
                }
            });

        listViewDetalleActualizado.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(DetalleAlquiler item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getCantidad() + item.getVajilla().getTipoDeVajilla().getNombreTipoDeVajilla() + " / Valor: " + item.getPrecioUnitario());
                }
            }
        });
    }







        @FXML
        public int buttonCrearAlquiler (ActionEvent event) throws IOException, SQLException {
            Alquiler alquiler;
            //CREAMOS EL ALQUILER
            LocalTime horaComienzo = null;
            LocalTime horaFinalizacion = null;
            LocalDate fecha=null;
            LocalDate fecha2=null;
            Multa multa = null;
            Integer diasDeAlquiler=null;
            Float costoDelivery=null;
            //MANEJO DE FECHAS

           try{
                fecha = txtFechaComienzoPicker.getValue();
                fecha2= txtFechaFinalizacionPicker.getValue();
               if (fecha == null || fecha2 == null) {
                   throw new NullPointerException("Las fechas no pueden ser nulas");
               }
               lblErrorFecha.setVisible(false);


           } catch (Exception e) {
               lblErrorFecha.setVisible(true);
               return 0;
           }

            try {
                String hora = horaComienzoPicker.getText();
                horaComienzo = LocalTime.parse(hora);
                String hora2 = horaFinalizacionPicker.getText();
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
            if(estado==null || cliente==null){
                lblErrorCamposVacios.setVisible(true);
                return 0;
            }



                alquiler = new Alquiler(fecha, fecha2, horaComienzo, horaFinalizacion, cliente, diasDeAlquiler, costoDelivery, 0, estado, listaDetalleAlquiler, multa, "");
                idGenerado=alquilerService.insertAlquiler(alquiler);
                if (idGenerado > 0) { // Solo si el ID generado es válido, habilitamos los campos

                    //YA SE CREO EL ALQUILER CORRECTAMENTE, SE HABILITAN LOS CAMPOS DEL DETALLE DEL ALQUILER
                    buttonAgregarDetalleAlquiler.setDisable(false);
                    txtCantidadVajilla.setDisable(false);
                    txtPrecioUnitarioVajilla.setDisable(false);
                    listViewTiposDeVajilla.setDisable(false);
                    listViewDetalleActualizado.setDisable(false);
                    searchFieldTipoDeVajilla.setDisable(false);


                    //SE DEJA DE MOSTRAR LOS CAMPOS DE CREACION DE ALQUILER
                    buttonVolver.setDisable(true);
                    txtFechaComienzoPicker.setDisable(true);
                    txtFechaFinalizacionPicker.setDisable(true);
                    horaComienzoPicker.setDisable(true);
                    horaFinalizacionPicker.setDisable(true);
                    txtDiasDeAlquiler.setDisable(true);
                    txtCostoDelivery.setDisable(true);
                    miComboBoxEstado.setDisable(true);
                    searchFieldClientes.setDisable(true);
                    listViewClientes.setDisable(true);
                    buttonCrearAlquiler.setDisable(true);

            } else {

                lblErrorCamposVacios.setVisible(true);
                return 0;
            }
            return idGenerado;
        }



    @FXML
    public float buttonAgregarDetalleAlquiler () throws IOException, SQLException {
        //UNA VEZ CREADO EL ALQUILER, VAMOS INGRESANDO LA VAJILLA, SE CREAN DETALLES Y SE AGREGAN A LA BASE DE DATOS.
        Integer cantidad = Integer.parseInt(txtCantidadVajilla.getText());
        float precioUnitario;
        TipoDeVajilla tipoDeVajilla= listViewTiposDeVajilla.getSelectionModel().getSelectedItem();
        Vajilla vajilla=vajillaService.traerVajillaPorTipo(tipoDeVajilla.getIdTipoDeVajilla());
        Stock stockDisponible=stockService.traerStock(vajilla.getIdVajilla());

        if(cantidad > stockDisponible.getCantidadDisponible()){
            lblErrorStockInsuficiente.setVisible(true);
            return 0;
        }else{
            Integer stockNuevo= stockDisponible.getCantidadDisponible()-cantidad;
            stockService.modificarStock(stockNuevo,stockDisponible.getIdStock());
        }
        if (txtPrecioUnitarioVajilla.getText().trim().isEmpty()) {
            precioUnitario = vajilla.getPrecioIndividual();
        } else {
            precioUnitario = Float.parseFloat(txtPrecioUnitarioVajilla.getText().trim());
        }
        Alquiler alquilerDetalle= alquilerService.traerAlquiler(idGenerado);
        DetalleAlquiler detalleAlquiler=new DetalleAlquiler(cantidad,precioUnitario,alquilerDetalle,vajilla);
        try {
            detalleAlquilerService.insertarDetalleAlquiler(detalleAlquiler);
            lblVajillaAgregada.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> lblVajillaAgregada.setVisible(false));
            pause.play();

        } catch (SQLException e) {
           lblVajillaAgregada.setVisible(false);

        }

        // Limpiar los campos después de agregar
                txtCantidadVajilla.clear();
                txtPrecioUnitarioVajilla.clear();
                searchFieldTipoDeVajilla.clear();
                listViewTiposDeVajilla.setVisible(false);
                listViewTiposDeVajilla.getSelectionModel().clearSelection();
        //Se actualiza la lista de alquiler modificada, agregando el detalle que sumamos
       listaDetalleAlquilerModificada = detalleAlquilerService.traerDetallesPorIdAlquiler(idGenerado);

        // Actualizar el ObservableList
        detallesObservableList.setAll(listaDetalleAlquilerModificada);

        //VAMOS CALCULANDO EL TOTAL PARA AGREGARSELO AL TOTAL ALQUILER
        totalAlquiler+=precioUnitario*cantidad;

        buttonDetalleAlquilerFinalizado.setDisable(false);
        return totalAlquiler;
    }

    @FXML
    public void buttonDetalleAlquilerFinalizado(ActionEvent event) throws IOException, SQLException {

        totalAlquiler=totalAlquiler+alquilerService.traerAlquiler(idGenerado).getCostoDelivery();


        try{
            alquilerService.modificarAlquiler(totalAlquiler,idGenerado);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        buttonVolver.setDisable(false);

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

