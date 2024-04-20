package br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.repository.ClienteRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClienteApplicationService implements ClienteService{
    private final ClienteRepository clienteRepository;

    @Override
    public ResponseEntity criaNovoCliente(ClienteRequest clienteRequest) {
        log.info("[inicio] - ClienteRestController - criaNovoCliente ");
              ResponseEntity response= clienteRepository.salva(new Cliente(clienteRequest));
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
}
