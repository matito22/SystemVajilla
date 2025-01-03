package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.Cliente;
import com.example.macdanyapp.entitys.Usuario;
import com.example.macdanyapp.entitys.UsuarioAwareController;
import com.example.macdanyapp.services.ClienteService;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;

public class CrearNuevoClienteController implements UsuarioAwareController {

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
    public TextField txtApellidoCliente;
    @FXML
    public TextField txtTelefonoCliente;
    @FXML
    public TextField txtDomicilioCliente;
    @FXML
    public TextField txtNombreCliente;
    @FXML
    public Button buttonCrear;

    @FXML
    public Label lblError;
    @FXML
    public Label lblCorrecto;
    ClienteService clienteService = new ClienteService();

    @FXML
    private boolean buttonCrear() throws SQLException {
        lblError.setVisible(false);
        lblCorrecto.setVisible(false);

        if(txtNombreCliente.getText().trim().isEmpty() || txtApellidoCliente.getText().trim().isEmpty() || txtTelefonoCliente.getText().trim().isEmpty() || txtDomicilioCliente.getText().trim().isEmpty()){
            lblError.setText("Hay campos vacios");
            lblError.setVisible(true);
            return false;
        }else{
            String nombre=txtNombreCliente.getText();
            String apellido=txtApellidoCliente.getText();
            String telefono=txtTelefonoCliente.getText();
            String domicilio=txtDomicilioCliente.getText();

            Cliente cliente=new Cliente(nombre,apellido,telefono,domicilio);
            clienteService.insertCliente(cliente);
            lblCorrecto.setVisible(true);
            //EL MENSAJE CORRECTO DURA 3 SEGUNDOS
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> lblCorrecto.setVisible(false));
            pause.play();
        }
        txtNombreCliente.clear();
        txtApellidoCliente.clear();
        txtTelefonoCliente.clear();
        txtDomicilioCliente.clear();

        return true;
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
