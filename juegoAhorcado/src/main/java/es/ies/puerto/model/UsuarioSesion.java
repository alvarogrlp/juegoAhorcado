package es.ies.puerto.model;

public class UsuarioSesion {
    private static UsuarioSesion instancia;
    private UsuarioEntity usuario;

    private UsuarioSesion() {
    }

    public static UsuarioSesion getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioSesion();
        }
        return instancia;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public void cerrarSesion() {
        usuario = null;
    }
}