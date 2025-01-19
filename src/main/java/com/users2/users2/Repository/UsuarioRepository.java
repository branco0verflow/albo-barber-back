package com.users2.users2.Repository;

import com.users2.users2.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Usando convenciones de nombres de Spring Data JPA
    Optional<Usuario> findByNombreUsuarioAndContra(String nombreUsuario, String contra);

    // O, con una consulta personalizada
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.contra = :contra")
    Optional<Usuario> buscarPorNombreUsuarioYContra(@Param("nombreUsuario") String nombreUsuario, @Param("contra") String contra);

    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
}

