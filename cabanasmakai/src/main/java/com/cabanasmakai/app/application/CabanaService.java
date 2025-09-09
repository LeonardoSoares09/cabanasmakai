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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
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
        return cabanaRepository.save(cabana);
    }

    @Transactional
    public Cabanas atualizarCabana(Cabanas cabana){
        Optional<Cabanas> cabanaExistente = cabanaRepository.findById(cabana.getId());
        if(cabanaExistente.isEmpty()){
            throw new CabanaNaoEncontradaException(cabana.getId());
        }

        Cabanas cabanaEditada = cabanaExistente.get();

        if(cabana.getStatusCabana() != null){
            cabanaEditada.setStatusCabana(cabana.getStatusCabana());
        }

        if(cabana.getNumeroCabana() != null){
            cabanaEditada.setNumeroCabana(cabana.getNumeroCabana());
        }

        return cabanaRepository.save(cabanaEditada);
    }

    public StatusCabana verificarStatusCabana(Long id){
        Cabanas cabana = cabanaRepository.findById(id).orElseThrow(()->new CabanaNaoEncontradaException(id));
        return cabana.getStatusCabana();
    }

    @Transactional
    public void excluirCabana(Long id) {
        if (reservaRepository.existsByCabanaIdAndStatus(id, StatusReserva.ATIVA)) {
            throw new CabanaComReservaAtivaException(id);
        }
        cabanaRepository.deleteById(id);
    }

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

    public List<Cabanas> listarCabanas() {
        return cabanaRepository.findAll();
    }

    public Cabanas listarCabanaId(Long id) {
        return cabanaRepository.findById(id).orElseThrow(() -> new CabanaNaoEncontradaException(id));
    }

    @Scheduled(cron = "0 0 13 * * ?", zone = "America/Sao_Paulo")
    public void verificarReservasFinalizadas() {
        List<Cabanas> cabanas = cabanaRepository.findAll();

        for (Cabanas cabana : cabanas){
            for(Reserva reserva : cabana.getReservas()){
                if(reserva.getStatus() == StatusReserva.ATIVA && reserva.getDataSaida().isBefore(LocalDate.now())){
                    reserva.setStatus(StatusReserva.FINALIZADA);
                    reservaRepository.save(reserva);
                    cabana.setStatusCabana(StatusCabana.DISPONIVEL);
                    cabanaRepository.save(cabana);
                }
            }
        }
    }
}
