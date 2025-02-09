package org.simarro.adt6_practica3.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Usuario {
    @Schema(description = "Identificador del usuario", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "Nombre del usuario", example = "Miroslava")
    @Column(nullable = false)
    private String nombre;

    @Schema(description = "Apellidos del usuario", example = "Ivanova Georgieva")
    @Size(min = 6, max = 50)
    @Column(nullable = false)
    private String apellidos;

    @Schema(description = "Correo del usuario", example = "miros-98@hotmail.es")
    @Email
    @Column(nullable = false)
    private String email;

    @Schema(description = "Contraseña del usuario", example = "122345G!")
    @Min(6)
    @Column(nullable = false)
    private String password;

    @Schema(description = "Fecha de creación del usuario", example = "09/02/2025")
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;

    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String apellidos, String email, String password, LocalDate fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public @Size(min = 6, max = 50) String getApellidos() {
        return apellidos;
    }

    public void setApellidos(@Size(min = 6, max = 50) String apellidos) {
        this.apellidos = apellidos;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}

