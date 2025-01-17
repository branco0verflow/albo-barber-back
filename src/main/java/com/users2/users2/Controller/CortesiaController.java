package com.users2.users2.Controller;

import com.users2.users2.Entity.Cortesia;
import com.users2.users2.Service.CortesiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cortesias")
class CortesiaController {
    @Autowired
    private CortesiaService cortesiaService;

    @GetMapping
    public List<Cortesia> getAllCortesias() {
        return cortesiaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Cortesia> getCortesiaById(@PathVariable Long id) {
        return cortesiaService.findById(id);
    }

    @PostMapping
    public Cortesia createCortesia(@RequestBody Cortesia cortesia) {
        return cortesiaService.save(cortesia);
    }

    @DeleteMapping("/{id}")
    public void deleteCortesia(@PathVariable Long id) {
        cortesiaService.deleteById(id);
    }

    // PUT para actualizar Cortesías
    @PutMapping("/{id}")
    public ResponseEntity<Cortesia> actualizarCortesia(@PathVariable Long id, @RequestBody Cortesia cortesia) {
        return cortesiaService.actualizarCortesia(id, cortesia)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

