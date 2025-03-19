package com.users2.users2.Controller;


import com.users2.users2.DTO.CierreCajaDTO;
import com.users2.users2.Entity.Gasto;
import com.users2.users2.Service.GastoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/gastos")
public class GastoController {
    private final GastoService gastoService;

    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    @PostMapping
    public ResponseEntity<Gasto> agregarGasto(@RequestBody Gasto gasto) {
        return ResponseEntity.ok(gastoService.guardarGasto(gasto));
    }

    @GetMapping
    public ResponseEntity<List<Gasto>> listarGastos() {
        return ResponseEntity.ok(gastoService.listarGastos());
    }

    @GetMapping("/cierre-caja")
    public ResponseEntity<CierreCajaDTO> obtenerCierreCaja(@RequestParam String fecha) {
        LocalDate fechaSeleccionada = LocalDate.parse(fecha);
        return ResponseEntity.ok(gastoService.calcularCierreCaja(fechaSeleccionada));
    }

    @GetMapping("/cierres-caja")
    public ResponseEntity<List<CierreCajaDTO>> obtenerCierresCaja(
            @RequestParam String fechaDesde,
            @RequestParam String fechaHasta) {

        LocalDate desde = LocalDate.parse(fechaDesde);
        LocalDate hasta = LocalDate.parse(fechaHasta);

        return ResponseEntity.ok(gastoService.calcularCierresEntreFechas(desde, hasta));
    }


    @GetMapping("/por-fecha")
    public List<Gasto> obtenerGastosPorFecha(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return gastoService.obtenerTodosLosGastosPorFecha(fecha);
    }

}

