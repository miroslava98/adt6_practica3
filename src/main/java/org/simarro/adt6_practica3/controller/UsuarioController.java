package org.simarro.adt6_practica3.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.simarro.adt6_practica3.dto.UsuarioRequestDTO;
import org.simarro.adt6_practica3.dto.UsuarioResponseDTO;
import org.simarro.adt6_practica3.model.Usuario;
import org.simarro.adt6_practica3.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Base de usuarios")
public class UsuarioController {


    @Autowired
    private IUsuarioService service;


    @Operation(summary = "Obtiene listado de usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listado usuarios",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))),
            @ApiResponse(
                    responseCode = "204",
                    description = "No hay usuarios",
                    content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> lista = service.listarTodos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene listado de usuarios por apellido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listado usuarios con apellido buscado",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))),
            @ApiResponse(
                    responseCode = "204",
                    description = "No hay usuarios con ese apellido",
                    content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/listarUsuariosXApellido")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuariosApellido(@RequestParam(value = "nombre") String nombre) {
        List<UsuarioResponseDTO> lista = service.listarUsuariosApellido(nombre);
        if (lista != null) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Operation(summary = "Obtiene usuario por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Existe usuario",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "El usuario no existe",
                    content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> listarPorId(@PathVariable("id") Integer id) {

        UsuarioResponseDTO usuarioResponseDTO = service.listarPorId(id);

        if (usuarioResponseDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioResponseDTO, HttpStatus.OK);
    }


    @Operation(summary = "Registra un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Se registra el usuario",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "204",
                    description = "El usuario no se ha registrado",
                    content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
    })
    @PostMapping
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = service.registrar(usuarioRequestDTO);
        if (usuario == null) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }


    @Operation(summary = "Modifica un usuario en la base")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Se modifica el usuario",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(
                    responseCode = "204",
                    description = "No se ha modificado el usuario",
                    content = @Content(schema = @Schema(implementation = Usuario.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificar(@PathVariable("id") Integer id, @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = service.modificar(id, usuarioRequestDTO);

        if (usuario == null) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }


    @Operation(summary = "Elimina un usuario")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Se elimina el usuario",
                    content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable("id") Integer id) {
        service.eliminar(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
