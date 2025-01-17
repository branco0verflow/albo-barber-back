package com.users2.users2.Repository;

import com.users2.users2.Entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    Socio findByNombreAndContra(String nombre, String contra);

}
