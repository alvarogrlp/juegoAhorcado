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
    private Button onVolverButton;
    @FXML
    private Button onEditarButton;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldUsuario;
    @FXML
    private TextField textFieldPassword;

    @FXML
    public void initialize() {
        UsuarioEntity usuario = UsuarioSesion.getInstancia().getUsuario();
        if (usuario != null) {
            textFieldEmail.setText(usuario.getEmail());
            textFieldUsuario.setText(usuario.getNombre());
            textFieldPassword.setText(usuario.getContrasenia());
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
}
