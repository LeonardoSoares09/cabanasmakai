package com.cabanasmakai.app.adapters.persistence;

import com.cabanasmakai.app.domain.Reserva;
import com.cabanasmakai.app.domain.enums.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByCabanaIdAndStatus(Long cabanaId, StatusReserva status);
    boolean existsByCabanaIdAndDataEntradaBeforeAndDataSaidaAfter(Long cabanaId, LocalDate dataEntrada, LocalDate dataSaida);
}
aaa
