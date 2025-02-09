package org.simarro.adt6_practica3.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.simarro.adt6_practica3.controller.UsuarioController;
import org.simarro.adt6_practica3.dto.UsuarioRequestDTO;
import org.simarro.adt6_practica3.dto.UsuarioResponseDTO;
import org.simarro.adt6_practica3.model.Usuario;
import org.simarro.adt6_practica3.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository repo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public UsuarioResponseDTO listarPorId(Integer id) {
        Optional<Usuario> usuarioOpt = repo.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            UsuarioResponseDTO usuarioResponseDTO = modelMapper.map(usuario, UsuarioResponseDTO.class);
            return usuarioResponseDTO;
        } else {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
    }


//    public UsuarioRequestDTO listarPorId1(Integer id) {
//        Optional<Usuario> op = repo.findById(id);
//        if (op.isPresent()) {
//            UsuarioRequestDTO usuarioDTO = modelMapper.map(op, UsuarioRequestDTO.class);
//            return usuarioDTO;
//        } else {
//            return null;
//        }
//    }


    @Override
    public List<UsuarioResponseDTO> listarTodos() {

        List<Usuario> listaUsuarios = repo.findAll();

        return listaUsuarios
                .stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuariosApellido(String nombre) {

        List<Usuario> listaUsuarios = repo.findAll();
        List<Usuario> listaUsuarioApellido = new ArrayList<>();


        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNombre().equals(nombre)) {
                listaUsuarioApellido.add(usuario);
            }
        }
        if (listaUsuarioApellido.isEmpty()) {
            throw new EntityNotFoundException("NO SE ENCONTRARON USUARIOS CON ESE NOMBRE");
        }
        return listaUsuarioApellido
                .stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Usuario registrar(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = modelMapper.map(usuarioRequestDTO, Usuario.class);
        usuario.setFechaCreacion(LocalDate.now());

        return repo.save(usuario);
    }

    @Override
    public Usuario modificar(Integer id, UsuarioRequestDTO usuarioRequestDTO) {

        Optional<Usuario> usuarioOpt = repo.findById(id);
        if (usuarioOpt.isPresent()) {

            Usuario usuario = usuarioOpt.get();
            usuario.setNombre(usuarioRequestDTO.getNombre());
            usuario.setApellidos(usuarioRequestDTO.getApellidos());
            usuario.setEmail(usuarioRequestDTO.getEmail());
            usuario.setPassword(usuarioRequestDTO.getPassword());


            return repo.save(usuario);
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);

    }


}
