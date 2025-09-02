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

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    @Transactional
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNaoEncontradoException(id);
        }
        clienteRepository.deleteById(id);
    }

    @Transactional
    public Cliente editaCliente (Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getId());
        if(clienteOptional.isEmpty()) {
            throw new ClienteNaoEncontradoException(cliente.getId());
        }

        Cliente clienteEditado = clienteOptional.get();

        if(cliente.getNome() != null){
            clienteEditado.setNome(cliente.getNome());
        }

        if(cliente.getCidade() != null){
            clienteEditado.setCidade(cliente.getCidade());
        }

        if(cliente.getTelefone() != null){
            clienteEditado.setTelefone(cliente.getTelefone());
        }

        if(cliente.getEmail() != null){
            clienteEditado.setEmail(cliente.getEmail());
        }

        if(cliente.getCpf() != null){
            clienteEditado.setCpf(cliente.getCpf());
        }

        return  clienteRepository.save(clienteEditado);
    }
}
