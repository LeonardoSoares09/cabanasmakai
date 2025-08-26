package com.cabanasmakai.app.application;

import com.cabanasmakai.app.adapters.persistence.CabanaRepository;
import com.cabanasmakai.app.adapters.persistence.ClienteRepository;
import com.cabanasmakai.app.adapters.persistence.ReservaRepository;
import com.cabanasmakai.app.domain.Cabanas;
import com.cabanasmakai.app.domain.Cliente;
import com.cabanasmakai.app.domain.Reserva;
import com.cabanasmakai.app.domain.enums.StatusCabana;
import com.cabanasmakai.app.domain.enums.StatusReserva;
import com.cabanasmakai.app.exceptions.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CabanaService {

    private final CabanaRepository cabanaRepository;
    private final ClienteRepository clienteRepository;
    private final ReservaRepository reservaRepository;

    public CabanaService(CabanaRepository cabanaRepository, ClienteRepository clienteRepository, ReservaRepository reservaRepository) {
        this.cabanaRepository = cabanaRepository;
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
    }

    @Transactional
    public Cabanas adicionarCabana(Cabanas cabana){
        cabana.setStatusCabana(StatusCabana.DISPONIVEL);
        return cabanaRepository.save(cabana);
    }

    @Transactional
    public Cabanas atualizarCabana(Cabanas cabana){
        Cabanas cabanaExistente = cabanaRepository.findById(cabana.getId())
                .orElseThrow(() -> new CabanaNaoEncontradaException(cabana.getId()));

        cabanaExistente.setNumeroCabana(cabana.getNumeroCabana());
        return cabanaRepository.save(cabanaExistente);
    }

    @Transactional
    public void excluirCabana(Long id) {
        if (reservaRepository.existsByCabanaIdAndStatus(id, StatusReserva.ATIVA)) {
            throw new CabanaComReservaAtivaException(id);
        }
        cabanaRepository.deleteById(id);
    }

    @Transactional
    public Cabanas reservarCabana(Long cabanaId, Long clienteId, LocalDate dataEntrada, LocalDate dataSaida) {
        Cabanas cabana = cabanaRepository.findById(cabanaId)
                .orElseThrow(() -> new CabanaNaoEncontradaException(cabanaId));

        if (reservaRepository.existsByCabanaIdAndDataEntradaBeforeAndDataSaidaAfter(cabanaId, dataSaida, dataEntrada)) {
            throw new CabanaOcupadaException();
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));

        Reserva reserva = new Reserva();
        reserva.setCabana(cabana);
        reserva.setCliente(cliente);
        reserva.setDataEntrada(dataEntrada);
        reserva.setDataSaida(dataSaida);
        reserva.setStatus(StatusReserva.ATIVA);

        cabana.setStatusCabana(StatusCabana.OCUPADA);
        cabanaRepository.save(cabana);
        reservaRepository.save(reserva);

        return cabana;
    }

    @Transactional
    public Cabanas liberaCabana(Long cabanaId) {
        Cabanas cabana = cabanaRepository.findById(cabanaId)
                .orElseThrow(() -> new CabanaNaoEncontradaException(cabanaId));

        Reserva reservaAtiva = cabana.getReservas().stream()
                .filter(reserva -> reserva.getStatus() == StatusReserva.ATIVA)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Não há reservas ativas para esta cabana"));

        reservaAtiva.setStatus(StatusReserva.FINALIZADA);

        cabana.setStatusCabana(StatusCabana.DISPONIVEL);

        reservaRepository.save(reservaAtiva);
        cabanaRepository.save(cabana);

        return cabana;
    }
}
