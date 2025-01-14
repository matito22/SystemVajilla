package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.*;
import com.example.macdanyapp.services.StockService;
import com.example.macdanyapp.services.TipoDeVajillaService;
import com.example.macdanyapp.services.VajillaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ModificarStockController implements UsuarioAwareController {

    @FXML
    public Button buttonAgregarNuevaVajilla;
    @FXML
    public TextField cantidadVajilla;
    @FXML
    public Button modificar;
    @FXML
    public Label lblError;
    @FXML
    public Button buttonVolver;
    @FXML
    public Label lblCorrecto;
    private List<Vajilla> listVajilla= new ArrayList<>();

    VajillaService vajillaService=new VajillaService();
    StockService stockService=new StockService();
    @FXML
    private TextField searchFieldVajilla;
    @FXML
    private ListView<Vajilla> listViewVajilla;
    private Usuario usuario;

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    @FXML
    public void initialize() throws SQLException {

        //CARGAMOS LA LISTA DE LOS TIPOS DE VAJILLA
        listVajilla = vajillaService.traerListaVajilla();
        ObservableList<Vajilla> VajillaObservableList = FXCollections.observableArrayList(listVajilla);
        VajillaObservableList.setAll(listVajilla);
        listViewVajilla.setItems(VajillaObservableList);

        listViewVajilla.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            protected void updateItem(Vajilla vajilla, boolean empty) {
                super.updateItem(vajilla, empty);
                if (empty || vajilla == null) {
                    setText(null);
                } else {
                    setText(vajilla.getTipoDeVajilla().getNombreTipoDeVajilla()); // Muestra el nombre y apellido
                }
            }
        });

        searchFieldVajilla.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            String filter = searchFieldVajilla.getText().toLowerCase();
            if (filter.isEmpty()) {
                listViewVajilla.setItems(FXCollections.observableArrayList());
                listViewVajilla.setVisible(false); // Oculta si no hay filtro
            } else {
                ObservableList<Vajilla> filteredList = FXCollections.observableArrayList(
                        listVajilla.stream()
                                .filter(vajilla -> vajilla.toString().toLowerCase().contains(filter))
                                .toList()
                );
                listViewVajilla.setItems(filteredList);
                listViewVajilla.setVisible(!filteredList.isEmpty()); // Muestra solo si hay resultados
            }
        });

        listViewVajilla.setOnMouseClicked(event -> {
            Vajilla selectedTipoDeVajilla = listViewVajilla.getSelectionModel().getSelectedItem();
            if (selectedTipoDeVajilla != null) {
                searchFieldVajilla.setText(selectedTipoDeVajilla.toString()); // Muestra el cliente seleccionado en el TextField

            }
        });

    }

    @FXML
    public int buttonModificar(ActionEvent event) {

        Vajilla vajilla = null;
        try {
            vajilla = listViewVajilla.getSelectionModel().getSelectedItem();
            lblError.setVisible(false);
        } catch (Exception e) {
            lblError.setText("Seleccione una vajilla");
            lblError.setVisible(true);
        }


        Integer cantidad;
        try {
            cantidad = Integer.parseInt(cantidadVajilla.getText());
            lblError.setVisible(false);
        } catch (Exception e) {
            lblError.setText("Cantidad incorrecta");
            lblError.setVisible(true);
            return 0;
        }


        Stock stock = new Stock(cantidad, vajilla);

        try{
            stockService.inserStock(stock);
            lblError.setVisible(false);
            lblCorrecto.setText("Stock Actualizado Correctamente");
            lblCorrecto.setVisible(true);
        } catch (Exception e) {
            lblError.setText("Error al insertar stock");
            lblCorrecto.setVisible(false);
            lblError.setVisible(true);

        }



        return 1;
    }



    @FXML
    public void buttonVolver(ActionEvent event) throws IOException {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/macdanyapp/template/Stock.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la nueva escena
        Object controller = loader.getController();
        if (controller instanceof UsuarioAwareController) {
            // Pasar el usuario al nuevo controlador
            ((UsuarioAwareController) controller).setUsuario(usuario);
        }

        // Cambiar de escena
        Scene scene = new Scene(root, 1600, 900);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
