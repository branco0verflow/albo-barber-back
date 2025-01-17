package com.users2.users2.Service;

import com.users2.users2.Entity.Usuario;
import com.users2.users2.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Long id, Usuario nuevosDatos) {
        return usuarioRepository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNombre(nuevosDatos.getNombre());
            usuarioExistente.setApellido(nuevosDatos.getApellido());
            usuarioExistente.setNombreUsuario(nuevosDatos.getNombreUsuario());
            usuarioExistente.setEmail(nuevosDatos.getEmail());
            usuarioExistente.setTelefono(nuevosDatos.getTelefono());
            return usuarioRepository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }
}
