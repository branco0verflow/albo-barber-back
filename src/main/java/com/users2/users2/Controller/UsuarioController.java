package com.users2.users2.Controller;

import com.users2.users2.Entity.Usuario;
import com.users2.users2.Repository.UsuarioRepository;
import com.users2.users2.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @GetMapping("/login/{nombreUsuario}/{contra}")
    public ResponseEntity<?> buscarUsuarioPorCredenciales(
            @PathVariable String nombreUsuario,
            @PathVariable String contra) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuarioAndContra(nombreUsuario, contra);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get()); // Retorna el usuario si existe
        } else {
            return ResponseEntity.status(404).body("Usuario o contraseña incorrectos");
        }
    }

    // Endpoint para obtener datos de un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para actualizar los datos de un usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario nuevosDatos) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, nuevosDatos);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<?> buscarUsuariosPorNombre(@PathVariable String nombre) {
        List<Usuario> usuarios = usuarioService.buscarPorNombre(nombre);
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron usuarios con el nombre: " + nombre);
        }
        return ResponseEntity.ok(usuarios);
    }



}

