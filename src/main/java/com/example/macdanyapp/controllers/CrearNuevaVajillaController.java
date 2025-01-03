package com.example.macdanyapp.controllers;

import com.example.macdanyapp.entitys.TipoDeVajilla;
import com.example.macdanyapp.entitys.Usuario;
import com.example.macdanyapp.entitys.UsuarioAwareController;
import com.example.macdanyapp.entitys.Vajilla;
import com.example.macdanyapp.services.TipoDeVajillaService;
import com.example.macdanyapp.services.VajillaService;
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

public class CrearNuevaVajillaController implements UsuarioAwareController {

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
    public TextField txtNombreTipoDeVajilla;
    @FXML
    public Label lblTipoVajillaAceptado;
    @FXML
    public TextField txtModeloVajilla;
    @FXML
    public TextField txtColorVajilla;
    @FXML
    public TextField txtTamanioVajilla;
    @FXML
    public TextField txtPrecioIndividualVajilla;
    @FXML
    public Label lblError;
    @FXML
    public Label lblCorrecto;


    TipoDeVajillaService tipoDeVajillaService=new TipoDeVajillaService();
    VajillaService vajillaService=new VajillaService();

    @FXML
    public void buttonCrearVajilla() throws SQLException {
        String nombreNuevoTipoDeVajilla = txtNombreTipoDeVajilla.getText();
        TipoDeVajilla tipoDeVajilla=new TipoDeVajilla(nombreNuevoTipoDeVajilla);
        tipoDeVajillaService.insertTipoDeVajilla(tipoDeVajilla);

        String modeloNuevoTipoDeVajilla = txtModeloVajilla.getText();
        String colorNuevoTipoDeVajilla = txtColorVajilla.getText();
        String tamanioNuevoTipoDeVajilla = txtTamanioVajilla.getText();
        Float precioNuevoTipoDeVajilla = Float.parseFloat(txtPrecioIndividualVajilla.getText());

        //SE CREA EL TIPO DE VAJILLA, PERO NO LA VAJILLA, REVISAAAAAAR

         Vajilla vajilla=new Vajilla(modeloNuevoTipoDeVajilla,colorNuevoTipoDeVajilla,precioNuevoTipoDeVajilla,tamanioNuevoTipoDeVajilla,tipoDeVajilla);

       try{
           vajillaService.insertVajilla(vajilla);
           lblCorrecto.setVisible(true);
           PauseTransition pause = new PauseTransition(Duration.seconds(3));
           pause.setOnFinished(event -> lblCorrecto.setVisible(false));
           pause.play();

       } catch (Exception e) {
           lblCorrecto.setVisible(false);
           lblError.setVisible(true);

       }



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
