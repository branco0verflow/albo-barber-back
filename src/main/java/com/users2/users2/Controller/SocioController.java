package com.users2.users2.Controller;

import com.users2.users2.Entity.Socio;
import com.users2.users2.Repository.SocioRepository;
import com.users2.users2.Service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/socios")
class SocioController {

    @Autowired
    private SocioService socioService;

    @GetMapping
    public List<Socio> getAllSocios() {
        return socioService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Socio> getSocioById(@PathVariable Long id) {
        return socioService.findById(id);
    }

    @PostMapping
    public Socio createSocio(@RequestBody Socio socio) {
        return socioService.save(socio);
    }

    @DeleteMapping("/{id}")
    public void deleteSocio(@PathVariable Long id) {
        socioService.deleteById(id);
    }

    @GetMapping("/login/{nombre}/{contra}")
    public ResponseEntity<Socio> login(@PathVariable String nombre, @PathVariable String contra) {
        Socio socio = socioService.login(nombre, contra);
        if (socio != null) {
            return ResponseEntity.ok(socio);
        } else {
            return ResponseEntity.status(404).build(); // Usuario o contraseña incorrectos
        }
    }

    // Endpoint para actualizar un barbero
    @PutMapping("/{id}")
    public ResponseEntity<Socio> actualizarSocio(@PathVariable Long id, @RequestBody Socio socioDetalles) {
        return socioService.actualizarSocio(id, socioDetalles)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
