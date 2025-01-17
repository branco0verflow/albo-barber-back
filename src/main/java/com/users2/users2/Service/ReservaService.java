package com.users2.users2.Service;
import com.users2.users2.Entity.Reserva;
import com.users2.users2.Repository.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> obtenerTodas() {
        return reservaRepository.findAll();
    }

    public Reserva guardar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<LocalTime> obtenerHorariosOcupados(Long socioId, LocalDate fecha) {
        // Filtra reservas por socio y fecha
        List<Reserva> reservas = reservaRepository.findBySocioIdAndFecha(socioId, fecha);
        // Extrae los horarios ocupados de las reservas
        return reservas.stream()
                .map(Reserva::getHora)
                .collect(Collectors.toList());
    }

    public List<Reserva> obtenerReservasPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Reserva> obtenerReservaPorId(Long reservaId) {
        return reservaRepository.findById(reservaId);
    }

    public void eliminarReserva(Long reservaId) {
        reservaRepository.deleteById(reservaId);
    }

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> obtenerReservasPorSocioYFecha(Long socioId, LocalDate fecha) {
        return reservaRepository.findBySocioIdAndFecha(socioId, fecha);
    }

    @Transactional
    public String confirmarReserva(Long reservaId) {
        Optional<Reserva> optionalReserva = reservaRepository.findById(reservaId);
        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            if (!reserva.getEstado()) { // Solo confirma si no está ya confirmado
                reserva.setEstado(true);
                reservaRepository.save(reserva);
                return "Reserva confirmada con éxito.";
            } else {
                return "La reserva ya estaba confirmada.";
            }
        } else {
            return "Reserva no encontrada.";
        }
    }

    public BigDecimal obtenerTotalFacturado(LocalDate fecha) {
        return reservaRepository.obtenerTotalFacturado(fecha);
    }

}

