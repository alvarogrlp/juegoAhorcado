package es.ies.puerto.controller;

import java.sql.SQLException;
import java.util.Optional;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.UsuarioEntity;
import es.ies.puerto.model.UsuarioSesion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditarController extends AbstractController {
    @FXML
    private Text textUsuario;
    @FXML
    private TextField textFieldUsuario;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private Text textContrasenia;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private PasswordField textFieldPasswordRepit;
    @FXML
    private Text textMensaje;
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button onVolverButton;
    @FXML
    private Button onEliminarButton;

    /**
     * * Metodo que inicializa el controlador
     */
    @FXML
    public void initialize() {
        UsuarioEntity usuario = UsuarioSesion.getInstancia().getUsuario();
        if (usuario != null) {
            textFieldEmail.setText(usuario.getEmail());
            textFieldUsuario.setText(usuario.getNombre());
            textFieldPassword.setText(usuario.getContrasenia());
        }
    }

    /**
     * * Metodo que se ejecuta al hacer click en el boton de guardar
     * @throws SQLException Excepcion de SQL
     */
    @FXML
    protected void onClickGuardar() throws SQLException {
        
        String emailOriginal = UsuarioSesion.getInstancia().getUsuario().getEmail();

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

        if (!getUsuarioServiceModel().editarUsuario(usuarioNuevo, emailOriginal)) {
            textMensaje.setText("No se pudo guardar el usuario");
            return;
        } else {
            textMensaje.setText("Usuario guardado Correctamente");
            return;
        }
    }

    /**
     * * Metodo que se ejecuta al hacer click en el boton de volver
     * * @throws SQLException Excepcion de SQL
     */
    @FXML
    protected void openVolverClick() {
        try {
            Stage stage = (Stage) onVolverButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("perfil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 760);
            stage.setTitle("Pantalla Inicio");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * * Metodo que se ejecuta al hacer click en el boton de eliminar
     * * @throws SQLException Excepcion de SQL
     */
    @FXML
    protected void openEliminarClick() {
        try {
            FXMLLoader loader = new FXMLLoader(PrincipalApplication.class.getResource("confirmacionEliminar.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Confirmación de eliminación");
            stage.setResizable(false);

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
