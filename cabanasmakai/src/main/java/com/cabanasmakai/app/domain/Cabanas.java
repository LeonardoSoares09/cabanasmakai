package com.cabanasmakai.app.domain;

import com.cabanasmakai.app.domain.enums.StatusCabana;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "cabanas")
public class Cabanas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroCabana;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCabana statusCabana;

    @OneToMany(mappedBy = "cabana", fetch = FetchType.EAGER)
    private List<Reserva> reservas;
}
