package es.ies.puerto.controller;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.UsuarioServiceModel;
import es.ies.puerto.model.UsuarioSesion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConfirmacionEliminarController extends AbstractController {

    @FXML
    private Button onAceptarButton;

    @FXML
    private Button onCancelarButton;

    @FXML
    private Text textMensaje;

    /**
     * * Metodo que inicializa el controlador
     * * @throws SQLException Excepcion de SQL
     */
    @FXML
    private void onAceptarClick() {
        try {
            String email = UsuarioSesion.getInstancia().getUsuario().getEmail();

            if (email == null || email.isEmpty()) {
                textMensaje.setText("No se pudo obtener el email del usuario.");
                return;
            }

            if (getUsuarioServiceModel().eliminarUsuario(email)) {
                System.out.println("Cuenta eliminada");

                Stage currentStage = (Stage) onAceptarButton.getScene().getWindow();
                currentStage.close();

                Stage mainStage = (Stage) PrincipalApplication.getCurrentStage();
                if (mainStage != null) {
                    mainStage.close();
                }

                Stage loginStage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 450, 760);
                loginStage.setTitle("Pantalla Inicio");
                loginStage.setScene(scene);
                loginStage.show();
            } else {
                textMensaje.setText("No se pudo eliminar la cuenta.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            textMensaje.setText("Ocurrió un error al intentar eliminar la cuenta.");
        }
    }

    /**
     * * Metodo que maneja el evento de cancelar la eliminacion de cuenta
     * * @param event Evento de click
     */
    @FXML
    private void onCancelarClick() {
        Stage stage = (Stage) onCancelarButton.getScene().getWindow();
        stage.close();
    }
}