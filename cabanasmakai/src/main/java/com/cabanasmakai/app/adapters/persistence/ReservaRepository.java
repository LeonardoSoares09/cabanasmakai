package com.cabanasmakai.app.adapters.persistence;

import com.cabanasmakai.app.domain.Reserva;
import com.cabanasmakai.app.domain.enums.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByCabanaIdAndStatus(Long cabanaId, StatusReserva status);
    boolean existsByCabanaIdAndDataEntradaBeforeAndDataSaidaAfter(Long cabanaId, LocalDate dataEntrada, LocalDate dataSaida);
}
