package es.ies.puerto.controller;

import java.util.ArrayList;
import java.util.List;
import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.config.ConfigManager;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.UsuarioEntity;
import es.ies.puerto.model.UsuarioSesion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController extends AbstractController {
    @FXML
    private TextField textFieldUsuario;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private Text textFieldMensaje;
    @FXML
    private Button onRegistrarButton;
    @FXML
    private Button onAceptarButton;
    @FXML
    private Text textUsuario;
    @FXML
    private Button onRecuperarButton;
    @FXML
    private Button onMostrarButton;
    @FXML
    private Text textContrasenia;
    @FXML
    private ComboBox comboIdioma;
    
    @FXML
    public void initialize() {
        List<String> idiomas = new ArrayList<>();
        idiomas.add("es");
        idiomas.add("en");
        idiomas.add("fr");
        comboIdioma.getItems().addAll(idiomas);
    }

    @FXML
    protected void cambiarIdioma() {
        String path = "src/main/resources/idioma-" + comboIdioma.getValue().toString() + ".properties";

        ConfigManager.ConfigProperties.setPath(path);

        textUsuario.setText(ConfigManager.ConfigProperties.getProperty("textUsuario"));
        textContrasenia.setText(ConfigManager.ConfigProperties.getProperty("textContrasenia"));
    }

    @FXML
    protected void onLoginButtonClick() {

        if (textFieldUsuario == null || textFieldUsuario.getText().isEmpty() ||
                textFieldPassword == null || textFieldPassword.getText().isEmpty()) {
            textFieldMensaje.setText("Credenciales vacias");
            return;
        }

        UsuarioEntity usuarioEntity = getUsuarioServiceModel().obtenerCredencialesUsuario(textFieldUsuario.getText());

        if (usuarioEntity == null) {
            textFieldMensaje.setText("El usuario no existe");
            return;
        }

        if ((textFieldUsuario.getText().equals(usuarioEntity.getEmail())
                || textFieldUsuario.getText().equals(usuarioEntity.getNombre()))
                        && textFieldPassword.getText().equals(usuarioEntity.getContrasenia())) {
            textFieldMensaje.setText("Usuario validado correctamente");
            openAceptarClick();
            return;

        }
        textFieldMensaje.setText("Credenciales invalidas");
    }

    @FXML
    protected void openRegistrarClick() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("registro.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 760);

            // RegistroController registroController = fxmlLoader.getController();
            // registroController.setpropertiesIdioma(this.getPropertiesIdioma());

            // registroController.postConstructor();

            Stage stage = (Stage) onRegistrarButton.getScene().getWindow();
            stage.setTitle("Pantalla Registro");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void openRecuperarClick() {

        try {
            Stage stage = (Stage) onRecuperarButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("recuperar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 760);
            stage.setTitle("Pantalla Recuperacion");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void openMostrarClick() {

        try {
            Stage stage = (Stage) onMostrarButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("mostrarUsuarios.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 760);
            stage.setTitle("Pantalla Recuperacion");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    protected void openAceptarClick() {
        try {
            UsuarioEntity usuarioEntity = getUsuarioServiceModel().obtenerCredencialesUsuario(textFieldUsuario.getText());

            if (usuarioEntity != null) {
                UsuarioSesion.getInstancia().setUsuario(usuarioEntity);

                Stage stage = (Stage) onAceptarButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("perfil.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 450, 760);
                stage.setTitle("Pantalla Perfil");
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Credenciales incorrectas");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}