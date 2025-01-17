package com.users2.users2.Service;

import com.users2.users2.Entity.Cortesia;
import com.users2.users2.Repository.CortesiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CortesiaService {
    @Autowired
    private CortesiaRepository cortesiaRepository;

    public List<Cortesia> findAll() {
        return cortesiaRepository.findAll();
    }

    public Optional<Cortesia> findById(Long id) {
        return cortesiaRepository.findById(id);
    }

    public Cortesia save(Cortesia cortesia) {
        return cortesiaRepository.save(cortesia);
    }

    public void deleteById(Long id) {
        cortesiaRepository.deleteById(id);
    }

    public Optional<Cortesia> actualizarCortesia(Long id, Cortesia cortesiaDetalles) {
        return cortesiaRepository.findById(id)
                .map(cortesia -> {
                    cortesia.setNombre(cortesiaDetalles.getNombre());
                    cortesia.setPrecio(cortesiaDetalles.getPrecio());
                    return cortesiaRepository.save(cortesia);
                });
    }
}