package com.users2.users2.Service;

import com.users2.users2.DTO.CierreCajaDTO;
import com.users2.users2.Entity.Gasto;
import com.users2.users2.Repository.GastoRepository;
import com.users2.users2.Repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class GastoService {
    private final GastoRepository gastoRepository;
    private final ReservaRepository reservaRepository;

    public GastoService(GastoRepository gastoRepository, ReservaRepository reservaRepository) {
        this.gastoRepository = gastoRepository;
        this.reservaRepository = reservaRepository;
    }

    public CierreCajaDTO calcularCierreCaja(LocalDate fecha) {
        BigDecimal facturacion = reservaRepository.obtenerTotalFacturado(fecha);
        BigDecimal gastos = gastoRepository.calcularGastosPorFecha(fecha);

        // Asegúrate de manejar valores nulos (si las consultas pueden devolver null)
        facturacion = facturacion != null ? facturacion : BigDecimal.ZERO;
        gastos = gastos != null ? gastos : BigDecimal.ZERO;

        return new CierreCajaDTO(facturacion, gastos, facturacion.subtract(gastos));
    }


    public Gasto guardarGasto(Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    public List<Gasto> listarGastos() {
        return gastoRepository.findAll();
    }

    public List<Gasto> obtenerTodosLosGastosPorFecha(LocalDate fecha){
       return gastoRepository.findAllByFecha(fecha);
    }

}
