package com.users2.users2.Controller;

import com.users2.users2.Entity.TipoCorte;
import com.users2.users2.Service.TipoCorteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-de-corte")
public class TipoCorteController {
    @Autowired
    private TipoCorteService tipoDeCorteService;

    @GetMapping
    public List<TipoCorte> obtenerTodos() {
        return tipoDeCorteService.obtenerTodos();
    }

    @PostMapping
    public TipoCorte guardar(@RequestBody TipoCorte tipoDeCorte) {
        return tipoDeCorteService.guardar(tipoDeCorte);
    }

    // PUT para actualizar Tipos de Corte
    @PutMapping("/{id}")
    public ResponseEntity<TipoCorte> actualizarTipoDeCorte(@PathVariable Long id, @RequestBody TipoCorte tipoDeCorte) {
        return tipoDeCorteService.actualizarTipoDeCorte(id, tipoDeCorte)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
        public void deleteTipoDeCorte(@PathVariable Long id){
            tipoDeCorteService.deleteById(id);
        }


}

