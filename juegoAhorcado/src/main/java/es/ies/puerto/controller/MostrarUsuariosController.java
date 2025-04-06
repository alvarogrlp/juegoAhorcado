package es.ies.puerto.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.UsuarioEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MostrarUsuariosController extends AbstractController {
    @FXML
    private Text textUsuarios;
    @FXML
    private Button onMostrarrButton;
    @FXML
    private Button onVolverButton;

    /**
     * * Metodo que inicializa el controlador
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
     * * Metodo que se ejecuta al hacer click en el boton de mostrar
     * * Muestra todos los usuarios en la base de datos
     * @throws SQLException Excepcion de SQL
     */
    @FXML
    protected void openMostrarClick() throws SQLException {

        String lista = "";
        ArrayList<UsuarioEntity> usuarios = getUsuarioServiceModel().obtenerUsuarios();

        for (UsuarioEntity usuarioEntity : usuarios) {
            lista += usuarioEntity.toString() + "\n";
        }
        textUsuarios.setText(lista);
    }
}