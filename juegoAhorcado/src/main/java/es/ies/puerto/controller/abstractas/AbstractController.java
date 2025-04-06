package es.ies.puerto.controller.abstractas;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import es.ies.puerto.model.UsuarioServiceModel;

public abstract class AbstractController {

    static final String PATH_DB = "src/main/resources/usuarios.db";

    private UsuarioServiceModel usuarioServiceModel;

    private Properties propertiesIdioma;

    /**
     * Constructor de la clase AbstractController.
     * Inicializa el modelo de usuario con la ruta de la base de datos.
     */
    public AbstractController() {
        try {
            usuarioServiceModel = new UsuarioServiceModel(PATH_DB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setpropertiesIdioma(Properties properties) {
        propertiesIdioma = properties;
    }

    public Properties getPropertiesIdioma() {
        return propertiesIdioma;
    }

    /**
     * Carga el archivo de propiedades del idioma especificado.
     * @param nombreFichero Nombre del fichero de propiedades.
     * @param idioma Idioma a cargar.
     * @return Un objeto Properties con las propiedades cargadas.
     */
    public Properties loadIdioma(String nombreFichero, String idioma) {
        Properties properties = new Properties();

        if (nombreFichero == null || idioma == null) {
            return properties;
        }

        String path = "src/main/resources/" + nombreFichero + "-" + idioma + ".properties";

        File file = new File(path);

        if (!file.exists() || !file.isFile()) {
            System.out.println("Path:" + file.getAbsolutePath());
            return properties;
        }

        try {
            FileInputStream input = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(input, "UTF-8");
            properties.load(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Devuelve el modelo de usuario.
     * @return El modelo de usuario.
     */
    public UsuarioServiceModel getUsuarioServiceModel() {
        return this.usuarioServiceModel;
    }
}
