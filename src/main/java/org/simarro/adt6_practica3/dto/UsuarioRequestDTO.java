package org.simarro.adt6_practica3.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {

    @NotBlank
    private String nombre;
    @Size(min = 6, max = 50, message = "Apellidos debe tener entre 6 y 50.")
    private String apellidos;
    @Email
    private String email;
    @Size(min = 4, max = 8)
    private String password;

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(String nombre, String apellidos, String email, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //nombre, apellidos, email, password
}
