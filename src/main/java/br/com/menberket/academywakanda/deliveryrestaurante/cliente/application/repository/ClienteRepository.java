package br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.repository;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository {
    ResponseEntity salva(Cliente cliente);

    ClienteResponse buscaClientePorId(UUID idCliente);

    List<ClienteResponse> listaDeClientes();
}
