package es.ies.puerto.controller;

import es.ies.puerto.model.UsuarioEntity;

import java.sql.SQLException;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.config.ConfigManager;
import es.ies.puerto.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegistroController extends AbstractController {
    @FXML
    private Button onVolverButton;
    @FXML
    private Button onRegistrarButton;
    @FXML
    private TextField textFieldUsuario;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private Text textMensaje;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private PasswordField textFieldPasswordRepit;
    @FXML
    private Text textUsuario;
    @FXML
    private Text textContrasenia;

    @FXML
    public void initialize() {
        textUsuario.setText(ConfigManager.ConfigProperties.getProperty("textUsuario"));
        textContrasenia.setText(ConfigManager.ConfigProperties.getProperty("textContrasenia"));
    }

    /**
     * public void postConstructor() {
     * textUsuario.setText(getPropertiesIdioma().getProperty("textUsuario"));
     * textContrasenia.setText(getPropertiesIdioma().getProperty("textContrasenia"));
     * }
     **/

    @FXML
    protected void onClickRegistar() throws SQLException {

        if (textFieldPassword == null || textFieldPassword.getText().isEmpty()
                || textFieldPasswordRepit == null || textFieldPasswordRepit.getText().isEmpty()) {
            textMensaje.setText("¡El password no puede ser nulo o vacio!");
            return;
        }

        if (textFieldPassword.getText().equals(textFieldPasswordRepit.getText())) {
            textMensaje.setText("¡El password es correcto!");
        }

        UsuarioEntity usuarioNuevo = new UsuarioEntity(textFieldEmail.getText(), textFieldUsuario.getText(),
                textFieldPassword.getText());

        if (!getUsuarioServiceModel().agregarUsuario(usuarioNuevo)) {
            textMensaje.setText("Usuario ya registrado o null");
            return;
        } else {
            textMensaje.setText("Usuario Registrado Correctamente");
            openVolverClick();
            return;
        }
    }

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