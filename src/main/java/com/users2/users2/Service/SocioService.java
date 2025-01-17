package com.users2.users2.Service;

import com.users2.users2.Entity.Socio;
import com.users2.users2.Repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocioService {
    @Autowired
    private SocioRepository socioRepository;

    public List<Socio> findAll() {
        return socioRepository.findAll();
    }

    public Optional<Socio> findById(Long id) {
        return socioRepository.findById(id);
    }

    public Socio save(Socio socio) {
        return socioRepository.save(socio);
    }

    public void deleteById(Long id) {
        socioRepository.deleteById(id);
    }

    public Socio login(String username, String password) {
        return socioRepository.findByNombreAndContra(username, password);
    }

    public Optional<Socio> actualizarSocio(Long id, Socio socioDetalles) {
        return socioRepository.findById(id)
                .map(socio -> {
                    socio.setNombre(socioDetalles.getNombre());
                    socio.setApellido(socioDetalles.getApellido());
                    socio.setImagenUrl(socioDetalles.getImagenUrl());
                    socio.setContra(socioDetalles.getContra());
                    socio.setAdmin(socioDetalles.isAdmin());
                    return socioRepository.save(socio);
                });
    }
}
