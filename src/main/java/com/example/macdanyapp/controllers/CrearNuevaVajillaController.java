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



    //FUNCIONA, SOLO FALTA QUE ADEMAS DE CREAR LA VAJILLA, SE DEBE AGREGAR EL STOCK
    @FXML
    public boolean buttonCrearVajilla() throws SQLException {
        String nombreNuevoTipoDeVajilla = txtNombreTipoDeVajilla.getText();
        TipoDeVajilla tipoDeVajilla=new TipoDeVajilla(nombreNuevoTipoDeVajilla);
        if(tipoDeVajillaService.traerTipoDeVajilla(nombreNuevoTipoDeVajilla)==null){
            tipoDeVajillaService.insertTipoDeVajilla(tipoDeVajilla);
            lblError.setVisible(false);

        }else{

            lblError.setText("El tipo de vajilla ya existe");
            lblError.setVisible(true);
            return false;
        }
        //REVISAR NO FUNCIONA

            String modeloNuevoTipoDeVajilla;
            String colorNuevoTipoDeVajilla;
            String tamanioNuevoTipoDeVajilla;
            Float precioNuevoTipoDeVajilla;



            if(txtModeloVajilla.getText().isEmpty()){
                modeloNuevoTipoDeVajilla=null;
            }else{
                modeloNuevoTipoDeVajilla=txtModeloVajilla.getText();
            }
            if(txtColorVajilla.getText().isEmpty()){
                colorNuevoTipoDeVajilla=null;
            }else{
                colorNuevoTipoDeVajilla=txtColorVajilla.getText();
            }

            if(txtTamanioVajilla.getText().isEmpty()){
                tamanioNuevoTipoDeVajilla=null;
            }else{
                tamanioNuevoTipoDeVajilla=txtTamanioVajilla.getText();
            }

           try{
              precioNuevoTipoDeVajilla=Float.parseFloat(txtPrecioIndividualVajilla.getText());
               lblError.setVisible(false);

           } catch (Exception e) {
               lblError.setText("El precio no es valido");
               lblError.setVisible(true);
               return false;
           }

           TipoDeVajilla tipoDeVajilla1=tipoDeVajillaService.traerTipoDeVajilla(nombreNuevoTipoDeVajilla);
           Vajilla vajilla=new Vajilla(modeloNuevoTipoDeVajilla,colorNuevoTipoDeVajilla,precioNuevoTipoDeVajilla,tamanioNuevoTipoDeVajilla,tipoDeVajilla1);
           System.out.println("ID DEL TIPO DE VAJILLA: "+vajilla.getTipoDeVajilla().getIdTipoDeVajilla());

       try{
           vajillaService.insertVajilla(vajilla);
           System.out.println("SE INSERTO VAJILLA");
           lblCorrecto.setVisible(true);
           PauseTransition pause = new PauseTransition(Duration.seconds(3));
           pause.setOnFinished(event -> lblCorrecto.setVisible(false));
           pause.play();

       } catch (Exception e) {
           lblCorrecto.setVisible(false);
           lblError.setVisible(true);
           return false;

       }


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
