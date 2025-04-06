package es.ies.puerto.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import es.ies.puerto.model.UsuarioSesion;
import es.ies.puerto.model.UsuarioEntity;

public class EstadisticasController {
    @FXML
    private Label nivelActualLabel;
    @FXML
    private Label victoriasLabel;
    @FXML
    private Label derrotasLabel;
    @FXML
    private Label rachaLabel;
    @FXML
    private Label victoriasNivelLabel;
    @FXML
    private Button cerrarButton;

    /**
     * * Método que se ejecuta al inicializar la ventana de estadísticas.
     * * Muestra las estadísticas del usuario actual en la interfaz gráfica.
     */
    @FXML
    public void initialize() {
        UsuarioEntity usuario = UsuarioSesion.getInstancia().getUsuario();
        
        nivelActualLabel.setText("Nivel actual: " + getNombreNivel(usuario.getNivelActual()));
        victoriasLabel.setText("Victorias totales: " + usuario.getVictoriasTotales());
        derrotasLabel.setText("Derrotas totales: " + usuario.getDerrotasTotales());
        rachaLabel.setText("Mayor racha: " + usuario.getMayorRacha());
        
        int victoriasRestantes = 3 - usuario.getVictoriasNivel();
        if (usuario.getNivelActual() == 3) {
            victoriasNivelLabel.setText("¡Has alcanzado el nivel máximo!");
        } else {
            victoriasNivelLabel.setText("Victorias para siguiente nivel: " + victoriasRestantes);
        }
    }

    /**
     * * Método que devuelve el nombre del nivel según su número.
     * @param nivel Número del nivel (1, 2 o 3).
     * @return Nombre del nivel correspondiente.
     */
    private String getNombreNivel(int nivel) {
        switch (nivel) {
            case 1: return "Fácil";
            case 2: return "Medio";
            case 3: return "Difícil";
            default: return "Desconocido";
        }
    }

    /**
     * * Método que se ejecuta al hacer clic en el botón de cerrar.
     * * Cierra la ventana de estadísticas.
     */
    @FXML
    private void onCerrarClick() {
        Stage stage = (Stage) cerrarButton.getScene().getWindow();
        stage.close();
    }
}