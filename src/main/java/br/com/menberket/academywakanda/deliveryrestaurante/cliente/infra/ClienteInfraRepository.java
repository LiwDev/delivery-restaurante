package br.com.menberket.academywakanda.deliveryrestaurante.cliente.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.repository.ClienteRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service.ClienteApplicationService;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service.ClienteService;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain.Entrega;
import br.com.menberket.academywakanda.deliveryrestaurante.handler.ApiException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bson.json.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.*;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ClienteInfraRepository implements ClienteRepository {
    private final MongoTemplate mongoTemplate;
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
    public void atualizaCliente(UUID idCliente, ClienteRequest clienteRequest) {
        log.info("[inicio] - ClienteApplicationService - atualizaCliente");
        UUID idClienteDB= buscaClientePorId(idCliente).getIdCliente();
        Query query = new Query();
        query.addCriteria(Criteria.where("idCliente").is(idClienteDB));
        Update update = new Update()
                .set("email",clienteRequest.getEmail())
                .set("enderco",clienteRequest.getEndereco())
                .set("cpf",clienteRequest.getCpf())
                .set("telefone",clienteRequest.getTelefone())
                .set("nomeCompleto",clienteRequest.getNomeCompleto());
       mongoTemplate.updateFirst(query, update, Entrega.class);
        log.info("[finaliza] - ClienteApplicationService - atualizaCliente");
    }

    @Override
    public List<ClienteResponse> mudaOrdemCliente(UUID idCliente, int linha) {
        log.info("[inicio] - ClienteApplicationService - mudaOrdemCliente");

        Cliente cliente = clienteSpringMongoDbRepository.findById(idCliente).get() ;
        log.info("idCliente = " + cliente);
        List<Cliente> clientes = clienteSpringMongoDbRepository.findAll();
        clientes.remove(cliente);
        clientes.add(linha,cliente);
        clientes.forEach(e->clienteSpringMongoDbRepository.save(e));

     // Update update = new Update().set("[]",clientes);
       // List<ClienteResponse> clienteResponse = mongoTemplate.(query,Cliente.class).stream().map(ClienteResponse::new).toList();
        log.info("[finaliza] - ClienteApplicationService - mudaOrdemCliente");

        return clientes.stream().map(ClienteResponse::new).toList();
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
