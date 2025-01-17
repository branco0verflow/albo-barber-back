package com.users2.users2.Repository;

import com.users2.users2.Entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    @Query("SELECT COALESCE(SUM(g.monto), 0) FROM Gasto g WHERE g.fecha = :fecha")
    BigDecimal calcularGastosPorFecha(LocalDate fecha);


    List<Gasto> findAllByFecha(LocalDate fecha);

}
