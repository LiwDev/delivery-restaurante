package br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.repository.ClienteRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClienteApplicationService implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Override
    public ResponseEntity criaNovoCliente(Cliente cliente) {
        log.info("[inicio] - ClienteRestController - criaNovoCliente ");
        ResponseEntity response = clienteRepository.salva(cliente);
        log.info("[finaliza] - ClienteRestController - criaNovoCliente ");
        return response;
    }

    @Override
    public ClienteResponse buscaClientePorId(UUID idCliente) {
        log.info("[inicio] - ClienteApplicationService - buscaClientePorId");
        ClienteResponse clienteResponse = clienteRepository.buscaClientePorId(idCliente);
        log.info("[finaliza] - ClienteApplicationService - buscaClientePorId");
        return clienteResponse;
    }

    @Override
    public List<ClienteResponse> listaDeClientes() {
        log.info("[inicio] - ClienteApplicationService - listaDeClientes");
        List<ClienteResponse> listaClientes = clienteRepository.listaDeClientes();
        log.info("[finaliza] - ClienteApplicationService - listaDeClientes");

        return listaClientes;
    }

    @Override
    public ResponseEntity deletaClientePorId(UUID idCliente) {
        log.info("[inicio] - ClienteApplicationService - deletaClientePorId");
        ResponseEntity deleteResponse = clienteRepository.deletaClientePorId(idCliente);
        log.info("[finaliza] - ClienteApplicationService - deletaClientePorId");


        return deleteResponse;
    }

    @Override
    public Cliente atualizaCliente(UUID idCliente, ClienteRequest clienteRequest) {
        log.info("[inicio] - ClienteApplicationService - atualizaCliente");
        ClienteResponse clienteResponse = buscaClientePorId(idCliente);
        Cliente cliente = editaCliente(clienteResponse, clienteRequest);
        clienteRepository.salva(cliente);
        log.info("[finaliza] - ClienteApplicationService - atualizaCliente");
        return cliente;
    }

    @Override
    public void deletaClientes() {
        log.info("[inicio] - ClienteApplicationService - deletaClientes");
        clienteRepository.deletaClientes();
        log.info("[inicio] - ClienteApplicationService - deletaClientes");
    }

    private Cliente editaCliente(ClienteResponse clienteResponse, ClienteRequest clienteRequest) {
        log.info("[inicio] - ClienteApplicationService - editaCliente");
        clienteResponse.setNomeCompleto(new Cliente(clienteRequest).getNomeCompleto());
        clienteResponse.setCpf(new Cliente(clienteRequest).getCpf());
        clienteResponse.setEmail(new Cliente(clienteRequest).getEmail());
        clienteResponse.setTelefone(new Cliente(clienteRequest).getTelefone());
        clienteResponse.setEndereco(new Cliente(clienteRequest).getEndereco());
        log.info("[finaliza] - ClienteApplicationService - editaCliente");
        return new Cliente(clienteResponse);

    }


}
