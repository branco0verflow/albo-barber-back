package com.users2.users2.Repository;

import com.users2.users2.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findBySocioIdAndFecha(Long socioId, LocalDate fecha);
    List<Reserva> findByUsuarioId(Long usuarioId);

    @Query("SELECT SUM(r.tipoDeCorte.precio) FROM Reserva r WHERE r.fecha = :fecha AND r.estado = true")
    BigDecimal obtenerTotalFacturado(@Param("fecha") LocalDate fecha);


}

