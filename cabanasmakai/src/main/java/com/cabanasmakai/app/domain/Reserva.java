package com.cabanasmakai.app.domain;

import com.cabanasmakai.app.domain.enums.StatusReserva;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cabana_id", nullable = false)
    private Cabanas cabana;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;
}
