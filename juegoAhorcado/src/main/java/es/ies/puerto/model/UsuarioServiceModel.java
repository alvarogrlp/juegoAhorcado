package es.ies.puerto.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.ies.puerto.model.abtrastas.Conexion;

public class UsuarioServiceModel extends Conexion {

    public UsuarioServiceModel() {
    }
    
    public UsuarioServiceModel(String unaRutaArchivoBD) throws SQLException {
        super(unaRutaArchivoBD);
    }
    
    public ArrayList<UsuarioEntity> obtenerUsuarios() throws SQLException {
        String sql = "SELECT * FROM Usuario";
        return obtenerUsuario(sql);
    }

    public ArrayList<UsuarioEntity> obtenerUsuario(String sql) throws SQLException {
        ArrayList<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();
        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String nombreStr = resultado.getString("nombre_usuario");
                String contraseniaStr = resultado.getString("contrasenia");
                String emailStr = resultado.getString("email");
                UsuarioEntity usuarioModel = new UsuarioEntity(emailStr, nombreStr, contraseniaStr);
                usuarios.add(usuarioModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return usuarios;
    }

    public UsuarioEntity obtenerCredencialesUsuario(String dato) {
        try {
            String sql = "SELECT * FROM Usuario " + "where email='" + dato + "'";
            ArrayList<UsuarioEntity> usuarios = obtenerUsuario(sql);
            if (usuarios.isEmpty()) {
                sql = "SELECT * FROM Usuario " + "where nombre_usuario='" + dato + "'";
                usuarios = obtenerUsuario(sql);
            }
            if (usuarios.isEmpty()) {
                return null;
            }
            return usuarios.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean agregarUsuario(UsuarioEntity usuario) throws SQLException {
        if (usuario == null) {
            return false;
        }
        ArrayList<UsuarioEntity> usuarios = obtenerUsuarios();
        String sql = "INSERT  INTO usuario (nombre_usuario,email,contrasenia) VALUES ('" + usuario.getNombre() + "', '"
                + usuario.getEmail() + "', '" + usuario.getContrasenia() + "')";

        if (usuarios.contains(usuario)) {
            return false;
        }

        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            sentencia.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return true;
    }

    public boolean editarUsuario(UsuarioEntity usuario) throws SQLException {
        if (usuario == null) {
            return false;
        }

        String sql = "UPDATE usuario SET nombre_usuario = '" + usuario.getNombre() + 
                     "', email = '" + usuario.getEmail() + 
                     "', contrasenia = '" + usuario.getContrasenia() + "'";

        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            sentencia.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }
    
    public boolean editarUsuario(UsuarioEntity usuario, String emailOriginal) throws SQLException {
        if (usuario == null || emailOriginal == null || emailOriginal.isEmpty()) {
            return false;
        }

        // Actualizar el registro basado en el email original
        String sql = "UPDATE usuario SET nombre_usuario = '" + usuario.getNombre() + 
                     "', email = '" + usuario.getEmail() + 
                     "', contrasenia = '" + usuario.getContrasenia() + 
                     "' WHERE email = '" + emailOriginal + "'";

        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            int filasActualizadas = sentencia.executeUpdate();
            return filasActualizadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }
}
