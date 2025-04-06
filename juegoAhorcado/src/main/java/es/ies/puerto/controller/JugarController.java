package es.ies.puerto.controller;

import es.ies.puerto.PrincipalApplication;
import es.ies.puerto.controller.abstractas.AbstractController;
import es.ies.puerto.model.UsuarioSesion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.Arrays;

public class JugarController extends AbstractController {
    @FXML
    private Text textUsuario;
    @FXML
    private Text textEmail;
    @FXML
    private Canvas ahorcadoCanvas;
    @FXML
    private Text textIntentos;
    @FXML
    private Text textPalabra;
    @FXML
    private TextField textFieldLetra;
    @FXML
    private Button onInsertarButton;
    @FXML
    private Text textMensaje;
    @FXML
    private Button onReiniciarButton;
    @FXML
    private Button onVolverButton;

    private String palabraSecreta;
    private char[] palabraAdivinada;
    private int intentosFallidos;
    private final int MAX_INTENTOS = 9; 
    private StringBuilder letrasUsadas; 

    /**
     * Metodo que inicializa el controlador
     * @throws SQLException Excepcion de SQL
     */
    @FXML
    public void initialize() {
        textUsuario.setText("Usuario: " + UsuarioSesion.getInstancia().getUsuario().getNombre());
        textEmail.setText("Email: " + UsuarioSesion.getInstancia().getUsuario().getEmail());
        
        textFieldLetra.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 1) {
                textFieldLetra.setText(newValue.substring(0, 1));
            }
        });
        
        iniciarJuego();
    }

    /**
     * Metodo que inicia el juego
     * @throws SQLException Excepcion de SQL
     */
    private void iniciarJuego() {
        intentosFallidos = 0;
        palabraSecreta = obtenerPalabraAleatoria();
        
        palabraAdivinada = new char[palabraSecreta.length()];
        Arrays.fill(palabraAdivinada, '_');
        
        letrasUsadas = new StringBuilder();
        
        actualizarInterfaz();
    }

    /**
     * Metodo que obtiene una palabra aleatoria de la base de datos
     * @return Palabra aleatoria
     * @throws SQLException Excepcion de SQL
     */
    private String obtenerPalabraAleatoria() {
        int nivelUsuario = UsuarioSesion.getInstancia().getUsuario().getNivelActual();
        return getUsuarioServiceModel().obtenerPalabraAleatoria(nivelUsuario);
    }

    /**
     * Metodo que actualiza la interfaz
     * @throws SQLException Excepcion de SQL
     */
    private void actualizarInterfaz() {
        StringBuilder palabraConEspacios = new StringBuilder();
        for (int i = 0; i < palabraAdivinada.length; i++) { 
            palabraConEspacios.append(palabraAdivinada[i]);
            if (i < palabraAdivinada.length - 1) {  
                palabraConEspacios.append(" ");
            }
        }
        textPalabra.setText("Palabra: " + palabraConEspacios.toString());
        
        textIntentos.setText("Letras fallidas: " + String.join("-", letrasUsadas.toString()));
        dibujarAhorcado();
    }

    /**
     * Metodo que reinicia el juego
     * @param event Evento de click
     * @throws SQLException Excepcion de SQL
     */
    @FXML
    protected void onReiniciarClick() {
        intentosFallidos = 0;
        palabraSecreta = obtenerPalabraAleatoria();
        palabraAdivinada = new char[palabraSecreta.length()];
        Arrays.fill(palabraAdivinada, '_');
        letrasUsadas = new StringBuilder();
        textFieldLetra.setDisable(false);
        onInsertarButton.setDisable(false);
        textMensaje.setText("");
        actualizarInterfaz();
        textFieldLetra.requestFocus();
    }

    /**
     * Metodo que dibuja el ahorcado en el canvas
     * @param gc GraphicsContext del canvas
     */
    private void dibujarAhorcado() {
        GraphicsContext gc = ahorcadoCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, ahorcadoCanvas.getWidth(), ahorcadoCanvas.getHeight());
        
        // Base
        gc.strokeLine(50, 300, 150, 300);
        
        if (intentosFallidos >= 1) {
            // Poste vertical
            gc.strokeLine(100, 300, 100, 50);
        }
        if (intentosFallidos >= 2) { 
            // Poste horizontal
            gc.strokeLine(100, 50, 200, 50);
        }
        if (intentosFallidos >= 3) { 
            // Cuerda
            gc.strokeLine(200, 50, 200, 80);
        }
        if (intentosFallidos >= 4) {
            // Cabeza
            gc.strokeOval(185, 80, 30, 30);
        }
        if (intentosFallidos >= 5) { 
            // Cuerpo
            gc.strokeLine(200, 110, 200, 200);
        }
        if (intentosFallidos >= 6) { 
            // Brazo izquierdo
            gc.strokeLine(200, 130, 170, 160);
        }
        if (intentosFallidos >= 7) {  
            // Brazo derecho
            gc.strokeLine(200, 130, 230, 160);
        }
        if (intentosFallidos >= 8) {  
            // Pierna izquierda
            gc.strokeLine(200, 200, 170, 250);
        }
        if (intentosFallidos >= 9) { 
            // Pierna derecha
            gc.strokeLine(200, 200, 230, 250);
        }
    }

    /**
     * Metodo que maneja el evento de insertar letra
     * @param event Evento de click
     */
    @FXML
    private void onInsertarClick() {
        if (textFieldLetra.getText().isEmpty()) {
            textMensaje.setText("Ingresa una letra");
            return;
        }

        char letra = textFieldLetra.getText().toLowerCase().charAt(0);
        boolean acierto = false;

        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                palabraAdivinada[i] = letra;
                acierto = true;
            }
        }

        if (!acierto && letrasUsadas.indexOf(String.valueOf(letra)) == -1) {
            if (letrasUsadas.length() > 0) {
                letrasUsadas.append("-");
            }
            letrasUsadas.append(letra);
            intentosFallidos++;
        }

        if (intentosFallidos >= MAX_INTENTOS) {
            textMensaje.setText("¡Perdiste! La palabra era: " + palabraSecreta);
            textFieldLetra.setDisable(true);
            onInsertarButton.setDisable(true);
            UsuarioSesion.getInstancia().getUsuario().actualizarEstadisticas(false);
            guardarEstadisticas();
        } else if (String.valueOf(palabraAdivinada).equals(palabraSecreta)) {
            textMensaje.setText("¡Felicidades! ¡Has ganado!");
            textFieldLetra.setDisable(true);
            onInsertarButton.setDisable(true);
            UsuarioSesion.getInstancia().getUsuario().actualizarEstadisticas(true);
            guardarEstadisticas();
        }

        actualizarInterfaz();
        textFieldLetra.clear();
        textFieldLetra.requestFocus();
    }

    /**
     * Metodo que guarda las estadisticas del usuario en la base de datos
     * @throws SQLException Excepcion de SQL
     */
    private void guardarEstadisticas() {
        try {
            if (!getUsuarioServiceModel().actualizarEstadisticas(UsuarioSesion.getInstancia().getUsuario())) {
                System.err.println("Error al guardar las estadisticas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error de base de datos al guardar las estadisticas");
        }
    }

    /**
     * Metodo que maneja el evento de ver estadisticas
     * @param event Evento de click
     */
    @FXML
    private void onVerEstadisticasClick() {
        try {
            FXMLLoader loader = new FXMLLoader(PrincipalApplication.class.getResource("estadisticas.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Estadísticas del Jugador");
            stage.setResizable(false);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que maneja el evento de volver a la pantalla de perfil
     * @param event Evento de click
     */
    @FXML
    protected void openVolverClick() {
        try {
            Stage stage = (Stage) onVolverButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("perfil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 760);
            stage.setTitle("Perfil");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
