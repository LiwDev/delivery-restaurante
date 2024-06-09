package br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    ResponseEntity criaNovoCliente(Cliente cliente);

    ClienteResponse buscaClientePorId(UUID idCliente);

    List<ClienteResponse> listaDeClientes();


    ResponseEntity deletaClientePorId(UUID idCliente);

    void atualizaCliente(UUID idCliente, ClienteRequest clienteRequest);

    void deletaClientes();

    List<ClienteResponse> mudaOrdemCliente(UUID idCliente, int linha);
}
