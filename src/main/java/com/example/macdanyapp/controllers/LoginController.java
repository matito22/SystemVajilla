package com.example.macdanyapp.controllers;

import com.example.macdanyapp.database.DatabaseConnection;
import com.example.macdanyapp.entitys.Cliente;
import com.example.macdanyapp.entitys.TipoDeUsuario;
import com.example.macdanyapp.entitys.Usuario;
import com.example.macdanyapp.services.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.macdanyapp.services.ClienteService;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.EventObject;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblError;

    @FXML
    private Label lblCorrecto;




    @FXML
    public Usuario buttonLogin(ActionEvent event) throws SQLException, IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/macdanyapp/template/TablaOpciones.fxml"));
                Parent root = loader.load();

                //pasamos el usuario al otro controlador
                TablaOpcionesController controller = loader.getController();
                // Crear una nueva escena
                Scene scene = new Scene(root,600,400);

                // Obtener el Stage actual y establecer la nueva escena
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.centerOnScreen();
                lblError.setVisible(false);
                lblCorrecto.setVisible(false);

                String usuario = txtUsuario.getText();
                String password = txtPassword.getText();

                //VERIFICAR QUE EL USUARIO EXISTE

                UsuarioService usuarioService = new UsuarioService();
                Usuario usuarioDB =usuarioService.traerUsuario(usuario);

                if(usuarioService.validarUsuario(usuarioDB)){
                    //pasamos el usuario al otro controlador
                    controller.setUsuario(usuarioDB);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();

                }else{
                    lblError.setText("Usuario Incorrecto");
                    lblError.setVisible(true);
                    lblCorrecto.setVisible(false);
                }
            return usuarioDB;
            }


}
