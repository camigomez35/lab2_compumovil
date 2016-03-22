package co.edu.udea.compumovil.gr12.lab2apprun.model;

import java.util.List;

/**
 * Created by SA on 21/03/2016.
 */
public class Usuario {
        private String nombre;
        private String contrasena;
        private String correo;
        private List<Carrera> carreras;

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }
}
