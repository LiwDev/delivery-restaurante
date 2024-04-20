package br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    ResponseEntity criaNovoCliente(ClienteRequest clienteRequest);

    ClienteResponse buscaClientePorId(UUID idCliente);

    List<ClienteResponse> listaDeClientes();
}
