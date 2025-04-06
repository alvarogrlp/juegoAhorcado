package es.ies.puerto.controller;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.UsuarioEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RecuperarController extends AbstractController {
    @FXML
    private TextField textFieldEmail;
    @FXML
    private Text textAviso;
    @FXML
    private Button onRecuperarButton;
    @FXML
    private Button onVolverButton;

    /**
     * * Metodo que inicializa el controlador
     * * @throws SQLException Excepcion de SQL
     */
    @FXML
    protected void onClickRecuperar() {

        if (textFieldEmail == null || textFieldEmail.getText().isEmpty()) {
            textAviso.setText("¡El password no puede ser nulo o vacio!");
            return;
        }

        UsuarioEntity usuarioEntity = getUsuarioServiceModel().obtenerCredencialesUsuario(textFieldEmail.getText());

        if (usuarioEntity == null) {
            textAviso.setText("El usuario no existe");
            return;
        }

        textAviso.setText("Recuperación enviada");
    }

    /**
     * * Metodo que se ejecuta al hacer click en el boton de volver
     * * @throws SQLException Excepcion de SQL
     */
    @FXML
    protected void openVolverClick() {
        try {
            Stage stage = (Stage) onVolverButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 760);
            stage.setTitle("Pantalla Inicio");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}