package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.*;
import com.example.macdanyapp.services.AlquilerService;
import com.example.macdanyapp.services.StockService;
import com.example.macdanyapp.services.TipoDeVajillaService;
import com.example.macdanyapp.services.VajillaService;
import javafx.animation.PauseTransition;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockController implements UsuarioAwareController {

    @FXML
    public Button buttonVolver;
    @FXML
    public Button buttonModificarStock;
    private Usuario usuario;

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    public Button buttonAgregarNuevaVajilla;
    @FXML
    private ListView<Stock> listViewStockDisponible;

    private List<Stock> listaStock=new ArrayList<Stock>();
    StockService stockService=new StockService();
    VajillaService vajillaService=new VajillaService();

    @FXML
    public void initialize() throws SQLException {


        listaStock = stockService.traerStockDisponible();
        ObservableList<Stock> stockObservableList = FXCollections.observableArrayList(listaStock);
        stockObservableList.setAll(listaStock);
        listViewStockDisponible.setItems(stockObservableList);

        // Define cómo se mostrará cada Cliente en el ListView
        listViewStockDisponible.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            protected void updateItem(Stock stock, boolean empty) {
                super.updateItem(stock, empty);
                if (empty || stock == null) {
                    setText(null);
                } else {
                    try {
                        setText(" "+ vajillaService.traerVajilla(stock.getVajilla().getIdVajilla()).getTipoDeVajilla().getNombreTipoDeVajilla() + " || " + stock.getCantidadDisponible());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    //Este boton crea un nuevo tipo de vajilla y crea una vajilla indicando, modelo,color ,tamanio y precio. Y adjuntando el tipo de vajilla nuevo.
    // Para agregarlo al stock finalmente
    @FXML
    public void buttonAgregarNuevaVajilla(ActionEvent event) throws SQLException, IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/macdanyapp/template/CrearNuevaVajilla.fxml"));
        Parent root = loader.load();
        Object controller = loader.getController();
        if (controller instanceof UsuarioAwareController) {
            // Pasar el usuario al nuevo controlador
            ((UsuarioAwareController) controller).setUsuario(usuario);
        }
        // Crear una nueva escena
        Scene scene = new Scene(root,1600,900);

        // Obtener el Stage actual y establecer la nueva escena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    public void buttonModificarStock(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/macdanyapp/template/ModificarStock.fxml"));
        Parent root = loader.load();
        Object controller = loader.getController();
        if (controller instanceof UsuarioAwareController) {
            // Pasar el usuario al nuevo controlador
            ((UsuarioAwareController) controller).setUsuario(usuario);
        }
        // Crear una nueva escena
        Scene scene = new Scene(root,1600,900);

        // Obtener el Stage actual y establecer la nueva escena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

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
