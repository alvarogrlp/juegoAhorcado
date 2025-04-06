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
    private ComboBox comboIdioma;
    @FXML
    private Text textUsuario;
    @FXML
    private TextField textFieldUsuario;
    @FXML
    private Text textContrasenia;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private Button onAceptarButton;
    @FXML
    private Text textFieldMensaje;
    @FXML
    private Button onRegistrarButton;
    @FXML
    private Button onRecuperarButton;
    @FXML
    private Button onMostrarButton;
    
    /**
     * * Metodo que inicializa el controlador
     * * @throws SQLException Excepcion de SQL
     */
    @FXML
    public void initialize() {
        List<String> idiomas = new ArrayList<>();
        idiomas.add("es");
        idiomas.add("en");
        idiomas.add("fr");
        comboIdioma.getItems().addAll(idiomas);
    }

    /**
     * * Metodo que se ejecuta al hacer click en el boton de idioma
     * * Cambia el idioma de la aplicacion
     * * @throws SQLException Excepcion de SQL
     */
    @FXML
    protected void cambiarIdioma() {
        String path = "src/main/resources/idioma-" + comboIdioma.getValue().toString() + ".properties";

        ConfigManager.ConfigProperties.setPath(path);

        textUsuario.setText(ConfigManager.ConfigProperties.getProperty("textUsuario"));
        textContrasenia.setText(ConfigManager.ConfigProperties.getProperty("textContrasenia"));
    }

    /**
     * * Metodo que se ejecuta al hacer click en el boton de login
     * * Valida las credenciales del usuario
     * * @throws SQLException Excepcion de SQL
     */
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

    /**
     * * Metodo que se ejecuta al hacer click en el boton de volver
     * * Vuelve a la pantalla de inicio
     */
    @FXML
    protected void openRegistrarClick() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("registro.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 760);

            Stage stage = (Stage) onRegistrarButton.getScene().getWindow();
            stage.setTitle("Pantalla Registro");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * * Metodo que se ejecuta al hacer click en el boton de recuperar
     * * Vuelve a la pantalla de recuperacion
     */
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

    /**
     * * Metodo que se ejecuta al hacer click en el boton de mostrar
     * * Vuelve a la pantalla de mostrar usuarios
     */
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
    
    /**
     * * Metodo que se ejecuta al hacer click en el boton de aceptar
     * * Valida las credenciales del usuario y abre la pantalla de perfil
     */
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