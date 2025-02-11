package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.*;
import com.example.macdanyapp.services.ClienteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.util.List;

public class ModificarAlquilerController  implements UsuarioAwareController {
    @FXML
    public DatePicker txtFechaComienzoPicker;
    @FXML
    public DatePicker txtFechaFinalizacionPicker;
    @FXML
    public TextField txtHoraComienzo;
    @FXML
    public Label lblErrorFecha;
    @FXML
    public TextField txtHoraFinalizacion;
    @FXML
    public Label lblErrorHorario;
    @FXML
    public TextField txtDiasDeAlquiler;
    @FXML
    public Label lblErrorDiasDeAlquiler;
    @FXML
    public TextField txtCostoDelivery;
    @FXML
    public Label lblErrorCostoDelivery;
    @FXML
    public ComboBox miComboBoxEstado;
    @FXML
    public TextField searchFieldClientes;
    @FXML
    public ListView listViewClientes;
    @FXML
    public Label lblErrorCamposVacios;
    @FXML
    public Label lblAlquilerModificado;
    @FXML
    public Label lblCorrecto;
    @FXML
    public Label lblError;
    @FXML
    private Usuario usuario;
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    private Alquiler alquiler;
    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;

    }

    public ClienteService clienteService=new ClienteService();
    private List<Cliente> clientes;

//ANTES DEL INITIALIZE SE VE EL ALQUILER PERO DENTRO NO (11/02)

    public void initialize() throws SQLException {





        clientes = clienteService.traerListaClientes();
        listViewClientes.setVisible(false);

        // Convierte la lista de clientes a ObservableList
        ObservableList<Cliente> clientesObservableList = FXCollections.observableArrayList(clientes);
        listViewClientes.setItems(clientesObservableList);

        // Define cómo se mostrará cada Cliente en el ListView
        listViewClientes.setCellFactory(param -> new javafx.scene.control.ListCell<Cliente>() {
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
                );
                listViewClientes.setItems(filteredList);
                listViewClientes.setVisible(!filteredList.isEmpty()); // Muestra solo si hay resultados
            }
        });

        listViewClientes.setOnMouseClicked(event2 -> {
            Cliente selectedCliente = (Cliente) listViewClientes.getSelectionModel().getSelectedItem();
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






        //LLENAMOS LOS CAMPOS CON LOS VALORES DEL ALQUILER SELECCIONADO



        txtFechaComienzoPicker.setValue(alquiler.getFechaComienzo());
        txtFechaFinalizacionPicker.setValue(alquiler.getFechaFinalizacion());
        String hora = alquiler.getHoraComienzoTime().toString();
        txtHoraComienzo.setText(hora);
        String hora2=alquiler.getHoraFinalizacionTime().toString();
        txtHoraFinalizacion.setText(hora2);
        txtDiasDeAlquiler.setText(alquiler.getDiasAlquiler().toString());
        txtCostoDelivery.setText(alquiler.getCostoDelivery().toString());
        Estado estado=alquiler.getEstado();
        miComboBoxEstado.setValue(estado);
        searchFieldClientes.setText(alquiler.getCliente().toString());
    }



}
