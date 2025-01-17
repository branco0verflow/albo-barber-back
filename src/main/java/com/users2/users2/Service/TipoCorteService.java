package com.users2.users2.Service;

import com.users2.users2.Entity.TipoCorte;
import com.users2.users2.Repository.TipoCorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoCorteService {
    @Autowired
    private TipoCorteRepository tipoDeCorteRepository;

    public List<TipoCorte> obtenerTodos() {
        return tipoDeCorteRepository.findAll();
    }

    public TipoCorte guardar(TipoCorte tipoDeCorte) {
        return tipoDeCorteRepository.save(tipoDeCorte);
    }

    public Optional<TipoCorte> actualizarTipoDeCorte(Long id, TipoCorte tipoDeCorteDetalles) {
        return tipoDeCorteRepository.findById(id)
                .map(tipoDeCorte -> {
                    tipoDeCorte.setNombre(tipoDeCorteDetalles.getNombre());
                    tipoDeCorte.setPrecio(tipoDeCorteDetalles.getPrecio());
                    return tipoDeCorteRepository.save(tipoDeCorte);
                });
    }

    public void deleteById(Long id){
         tipoDeCorteRepository.deleteById(id);
    }
}

