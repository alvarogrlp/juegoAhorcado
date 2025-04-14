package es.ies.puerto.controller;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.model.UsuarioEntity;
import es.ies.puerto.model.UsuarioSesion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PerfilController {
    @FXML
    private TextField textFieldUsuario;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private Button onEditarButton;
    @FXML
    private Button onJugarButton;
    @FXML
    private Button onVolverButton;

    /**
     * * Metodo que inicializa el controlador
     * * @throws SQLException Excepcion de SQL
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
     * * Metodo que se ejecuta al hacer click en el boton de editar
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
    
    /**
     * * Metodo que se ejecuta al hacer click en el boton de editar
     * * @throws SQLException Excepcion de SQL
     */
    @FXML
    protected void openEditarClick() {

        try {
            Stage stage = (Stage) onVolverButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("editar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 760);
            stage.setTitle("Pantalla Inicio");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * * Metodo que se ejecuta al hacer click en el boton de jugar
     * * @throws SQLException Excepcion de SQL
     */
    @FXML
    protected void openJugarClick() {

        try {
            Stage stage = (Stage) onJugarButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("jugar.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 650, 860);
            stage.setTitle("Pantalla Inicio");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
