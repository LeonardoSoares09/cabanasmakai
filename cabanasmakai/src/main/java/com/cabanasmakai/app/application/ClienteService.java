package com.cabanasmakai.app.application;

import com.cabanasmakai.app.adapters.persistence.ClienteRepository;
import com.cabanasmakai.app.domain.Cliente;
import com.cabanasmakai.app.exceptions.ClienteNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNaoEncontradoException(id);
        }
        clienteRepository.deleteById(id);
    }

    @Transactional
    public Cliente editarCliente(Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getId());
        if(clienteOptional.isEmpty()) {
            throw new ClienteNaoEncontradoException(cliente.getId());
        }

        Cliente clienteAtualizado = clienteOptional.get();
        clienteAtualizado.setNome(cliente.getNome());
        clienteAtualizado.setEmail(cliente.getEmail());
        clienteAtualizado.setTelefone(cliente.getTelefone());
        clienteAtualizado.setCpf(cliente.getCpf());
        clienteAtualizado.setCidade(cliente.getCidade());
        clienteAtualizado.setDataEntrada(cliente.getDataEntrada());
        clienteAtualizado.setDataSaida(cliente.getDataSaida());
        return clienteRepository.save(clienteAtualizado);
    }
}
