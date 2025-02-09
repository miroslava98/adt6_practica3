package org.simarro.adt6_practica3.repository;

import org.simarro.adt6_practica3.dto.UsuarioResponseDTO;
import org.simarro.adt6_practica3.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("FROM Usuario u where u.apellidos LIKE %:nombre%")
    List<UsuarioResponseDTO> listarUsuariosApellido(@Param("nombre") String nombre);


}
