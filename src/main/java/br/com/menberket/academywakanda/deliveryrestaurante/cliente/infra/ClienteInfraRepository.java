package br.com.menberket.academywakanda.deliveryrestaurante.cliente.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.repository.ClienteRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service.ClienteApplicationService;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service.ClienteService;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.json.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ClienteInfraRepository implements ClienteRepository {

    private final ClienteSpringMongoDbRepository clienteSpringMongoDbRepository;


    @Override
    public ResponseEntity salva(Cliente cliente) {
        log.info("[incio] - ClienteInfraRepository - salva");
        clienteSpringMongoDbRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ClienteResponse buscaClientePorId(UUID idCliente) {
        log.info("[inicio] - ClienteInfraRepository - buscaClientePorId");
        Cliente cliente = clienteSpringMongoDbRepository.findById(idCliente).get();
        ClienteResponse clienteResponse = new ClienteResponse(cliente);
        log.info("[finaliza] - ClienteInfraRepository - buscaClientePorId");
        return clienteResponse;
    }

    @Override
    public List<ClienteResponse> listaDeClientes() {
        log.info("[inicio] - ClienteInfraRepository - listaDeClientes");
        List<Cliente> listaClientes = clienteSpringMongoDbRepository.findAll();
        List<ClienteResponse> listaClientesResponse = convertListEmResponse(listaClientes);
        log.info("[finaliza] - ClienteInfraRepository - listaDeClientes");
        return listaClientesResponse;
    }

    @Override
    public ResponseEntity deletaClientePorId(UUID idCliente) {
        log.info("[inicio] - ClienteInfraRepository - deletaClientePorId");
        clienteSpringMongoDbRepository.deleteById(idCliente);
        log.info("[finaliza] - ClienteInfraRepository - deletaClientePorId");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public void deletaClientes() {
        log.info("[inicio] - ClienteInfraRepository - deletaClientes");
        clienteSpringMongoDbRepository.deleteAll();
        log.info("[inicio] - ClienteInfraRepository - deletaClientes");
    }

    public List<ClienteResponse> convertListEmResponse(List<Cliente> listaClientes) {
        log.info("[inicio] - ClienteInfraRepository - convertListEmResponse");
        List<ClienteResponse> listaClientesResponse = new ArrayList<ClienteResponse>();
        listaClientes.forEach(p -> {
            listaClientesResponse.add(new ClienteResponse(p));
        });
        log.info("[finaliza] - ClienteInfraRepository - convertListEmResponse");
        return listaClientesResponse;
    }
}
