package com.users2.users2.Controller;

import com.users2.users2.Entity.Reserva;
import com.users2.users2.Repository.ReservaRepository;
import com.users2.users2.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> obtenerTodas() {
        return reservaService.obtenerTodas();
    }

    @PostMapping
    public Reserva guardar(@RequestBody Reserva reserva) {
        return reservaService.guardar(reserva);
    }

    @GetMapping("/horarios-disponibles/{socioId}")
    public List<LocalTime> obtenerHorariosDisponibles(
            @PathVariable Long socioId,
            @RequestParam int anio,
            @RequestParam int mes,
            @RequestParam int dia) {

        System.out.println("Socio ID: " + socioId);
        System.out.println("Año: " + anio);
        System.out.println("Mes: " + mes);
        System.out.println("Día: " + dia);

        // 1. Generar todos los horarios posibles
        List<LocalTime> todosLosHorarios = generarHorarios();

        // 2. Consultar horarios ocupados para el socio y la fecha proporcionados
        LocalDate fecha = LocalDate.of(anio, mes, dia);
        List<LocalTime> horariosOcupados = reservaService.obtenerHorariosOcupados(socioId, fecha);

        // 3. Filtrar horarios ocupados
        List<LocalTime> horariosDisponibles = todosLosHorarios.stream()
                .filter(horario -> !horariosOcupados.contains(horario))
                .toList();

        return horariosDisponibles;
    }

    private List<LocalTime> generarHorarios() {
        List<LocalTime> horarios = new ArrayList<>();
        LocalTime horaInicio = LocalTime.of(10, 0); // 10:00 A.M.
        LocalTime horaFin = LocalTime.of(20, 0);  // 8:00 P.M.
        while (horaInicio.isBefore(horaFin)) {
            horarios.add(horaInicio);
            horaInicio = horaInicio.plusMinutes(40); // Incrementar en 40 minutos
        }
        return horarios;
    }

    // Buscar reservas por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> obtenerReservasPorUsuario(@PathVariable Long usuarioId) {
        List<Reserva> reservas = reservaService.obtenerReservasPorUsuario(usuarioId);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    // Eliminar una reserva
    @DeleteMapping("/{reservaId}")
    public ResponseEntity<String> eliminarReserva(@PathVariable Long reservaId) {
        Optional<Reserva> reservaOptional = reservaService.obtenerReservaPorId(reservaId);
        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            if (!reserva.isEstado()) { // Verificar que el estado sea false antes de eliminar
                reservaService.eliminarReserva(reservaId);
                return ResponseEntity.ok("Reserva eliminada correctamente.");
            } else {
                return ResponseEntity.badRequest().body("No se puede cancelar una reserva ya realizada.");
            }
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/por-socio-y-fecha")
    public ResponseEntity<List<Reserva>> obtenerReservasPorSocioYFecha(
            @RequestParam Long socioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<Reserva> reservas = reservaService.obtenerReservasPorSocioYFecha(socioId, fecha);
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    // Cambiar el estado de una reserva a true
    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<String> confirmarReserva(@PathVariable Long id) {
        String result = reservaService.confirmarReserva(id);
        if (result.equals("Reserva confirmada con éxito.")) {
            return ResponseEntity.ok(result);
        } else if (result.equals("Reserva no encontrada.")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.badRequest().body(result); // Para casos como "ya estaba confirmada"
        }
    }

    @GetMapping("/facturacion")
    public ResponseEntity<BigDecimal> obtenerTotalFacturado(@RequestParam LocalDate fecha) {
        return ResponseEntity.ok(reservaService.obtenerTotalFacturado(fecha));
    }
}
