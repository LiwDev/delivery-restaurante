package br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service.ClienteService;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ClienteRestController implements ClienteApi {

   private final ClienteService clienteService;

    @Override
    public ResponseEntity criaNovoCliente(ClienteRequest clienteRequest) {
        log.info("[inicio] - ClienteRestController - criaNovoCliente ");
       ResponseEntity response = clienteService.criaNovoCliente(new Cliente(clienteRequest));
        log.info("[finaliza] - ClienteRestController - criaNovoCliente ");
        return response;

    }

    @Override
    public ResponseEntity<ClienteResponse> buscaClientePorId(UUID idCliente) {
        log.info("[inicio] - ClienteRestController - buscaClientePorId");
        ClienteResponse clienteResponse = clienteService.buscaClientePorId(idCliente);
        log.info("[finaliza] - ClienteRestController - buscaClientePorId");
        return ResponseEntity.ok(clienteResponse);
    }

    @Override
    public ResponseEntity<List<ClienteResponse>> listaDeClientes() {
        log.info("[inicio] - ClienteRestController - listaDeClientes");
        List<ClienteResponse> listaClientes = clienteService.listaDeClientes();
        log.info("[finaliza] - ClienteRestController - listaDeClientes");
        return ResponseEntity.ok(listaClientes);
    }

    @Override
    public ResponseEntity<ClienteResponse> atualizaCliente(UUID idCliente, ClienteRequest clienteRequest) {
        log.info("[inicio] - ClienteRestController - atualizaCliente");
        Cliente clienteResponseEntity= clienteService.atualizaCliente(idCliente,clienteRequest) ;
        log.info("[finaliza] - ClienteRestController - atualizaCliente");
        return ResponseEntity.ok(new ClienteResponse(clienteResponseEntity));
    }

    @Override
    public ResponseEntity deletaClientePorId(UUID idCliente) {
        log.info("[inicio] - ClienteRestController - deletaClientePorId");
        ResponseEntity deleteResponse = clienteService.deletaClientePorId(idCliente);
        log.info("[finaliza] - ClienteRestController - deletaClientePorId");
        return deleteResponse;


    }

    @Override
    public ResponseEntity deletaClientes() {
        log.info("[inicio] - ClienteRestController - deletaClientes");
        clienteService.deletaClientes();
        log.info("[finaliza] - ClienteRestController - deletaClientes");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
