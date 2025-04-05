package es.ies.puerto.model;

import java.util.Objects;

public class UsuarioEntity {

    private String email;
    private String nombre;
    private String contrasenia;
    private int nivelActual;
    private int nivelMaximo;
    private int puntuacion;
    private double progreso;

    public UsuarioEntity() {
    }

    public UsuarioEntity(String email, String nombre, String contrasenia) {
        this.email = email;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.nivelActual = 1;
        this.nivelMaximo = 1;
        this.puntuacion = 0;
        this.progreso = 0.0;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity usuarioEntity = (UsuarioEntity) o;
        return Objects.equals(email, usuarioEntity.email) && Objects.equals(nombre, usuarioEntity.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
  
    @Override
    public String toString() {
        return
            " email='" + getEmail() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", contrasenia='" + getContrasenia() + "'";
    }
}
