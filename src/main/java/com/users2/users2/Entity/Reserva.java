package com.users2.users2.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("fechaSeleccionada")
    private LocalDate fecha;
    @JsonProperty("horarioSeleccionado")
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "tipo_de_corte_id", nullable = false)
    private TipoCorte tipoDeCorte;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private boolean estado = false; // Indica si la reserva está pendiente o hecha

    @ManyToOne
    @JoinColumn(name = "cortesia_id")
    private Cortesia cortesia;

    @ManyToOne
    @JoinColumn(name = "socio_id")
    private Socio socio;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public TipoCorte getTipoDeCorte() {
        return tipoDeCorte;
    }

    public void setTipoDeCorte(TipoCorte tipoDeCorte) {
        this.tipoDeCorte = tipoDeCorte;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public boolean getEstado(){
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cortesia getCortesia() {
        return cortesia;
    }

    public void setCortesia(Cortesia cortesia) {
        this.cortesia = cortesia;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Reserva(Long id, LocalDate fecha, LocalTime hora, TipoCorte tipoDeCorte, Usuario usuario, boolean estado, Cortesia cortesia, Socio socio) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoDeCorte = tipoDeCorte;
        this.usuario = usuario;
        this.estado = estado;
        this.cortesia = cortesia;
        this.socio = socio;
    }

    public Reserva(){}

    public Reserva(Long id, Socio socio, LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.socio = socio;
        this.fecha = fecha;
        this.hora = hora;
    }

}

