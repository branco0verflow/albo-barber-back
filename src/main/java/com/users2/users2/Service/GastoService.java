package com.users2.users2.Service;

import com.users2.users2.DTO.CierreCajaDTO;
import com.users2.users2.Entity.Gasto;
import com.users2.users2.Repository.GastoRepository;
import com.users2.users2.Repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
        BigDecimal noMonetario = reservaRepository.obtenerTotalNoMonetario(fecha);

        // Asegúrate de manejar valores nulos (si las consultas pueden devolver null)
        facturacion = facturacion != null ? facturacion : BigDecimal.ZERO;
        gastos = gastos != null ? gastos : BigDecimal.ZERO;
        noMonetario = noMonetario != null ? noMonetario : BigDecimal.ZERO;

        return new CierreCajaDTO(facturacion, gastos, facturacion.subtract(gastos), noMonetario);
    }

    public List<CierreCajaDTO> calcularCierresEntreFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        List<CierreCajaDTO> cierres = new ArrayList<>();
        LocalDate fechaActual = fechaDesde;

        while (!fechaActual.isAfter(fechaHasta)) {
            BigDecimal facturacion = reservaRepository.obtenerTotalFacturado(fechaActual);
            BigDecimal gastos = gastoRepository.calcularGastosPorFecha(fechaActual);
            BigDecimal noMonetario = reservaRepository.obtenerTotalNoMonetario(fechaActual);

            // Evitar valores nulos
            facturacion = facturacion != null ? facturacion : BigDecimal.ZERO;
            gastos = gastos != null ? gastos : BigDecimal.ZERO;
            noMonetario = noMonetario != null ? noMonetario : BigDecimal.ZERO;

            CierreCajaDTO cierre = new CierreCajaDTO(facturacion, gastos, facturacion.subtract(gastos), noMonetario);
            cierres.add(cierre);

            fechaActual = fechaActual.plusDays(1); // Avanzar al siguiente día
        }

        return cierres;
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
