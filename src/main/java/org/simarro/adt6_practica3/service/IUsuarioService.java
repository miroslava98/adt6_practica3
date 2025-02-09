package org.simarro.adt6_practica3.service;


import org.simarro.adt6_practica3.dto.UsuarioRequestDTO;
import org.simarro.adt6_practica3.dto.UsuarioResponseDTO;
import org.simarro.adt6_practica3.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    // 1. Búsqueda de un usuario concreto.

    UsuarioResponseDTO listarPorId(Integer id);

    // 2. Búsqueda de todos los usuarios.

    List<UsuarioResponseDTO> listarTodos();

    //3.Registro de un nuevo usuario.
    Usuario registrar(UsuarioRequestDTO usuarioRequestDTO);

    //4.Modificar un usuario.

    Usuario modificar(Integer id, UsuarioRequestDTO usuarioRequestDTO);
    //5. Dar de baja un usuario.

    void eliminar(Integer id);

    List<UsuarioResponseDTO> listarUsuariosApellido(String nombre);
}
