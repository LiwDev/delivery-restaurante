package br.com.menberket.academywakanda.deliveryrestaurante.cliente.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.repository.ClienteRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import lombok.extern.log4j.Log4j2;
import org.bson.json.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Log4j2
public class ClienteInfraRepository implements ClienteRepository {
   @Autowired
    ClienteSpringMongoDbRepository clienteSpringMongoDbRepository;

    @Override
    public ResponseEntity salva(Cliente cliente) {
        log.info("[incio] - ClienteInfraRepository - salva");
        clienteSpringMongoDbRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ClienteResponse buscaClientePorId(UUID idCliente) {
        log.info("[inicio] - ClienteInfraRepository - buscaClientePorId");
        Cliente cliente =  clienteSpringMongoDbRepository.findById(idCliente).get();
        ClienteResponse clienteResponse = new ClienteResponse(cliente);
        log.info("[finaliza] - ClienteInfraRepository - buscaClientePorId");
        return clienteResponse;
    }

    @Override
    public List<ClienteResponse> listaDeClientes() {
        log.info("[inicio] - ClienteInfraRepository - listaDeClientes");
        List<Cliente> listaClientes = clienteSpringMongoDbRepository.findAll();
        List<ClienteResponse> listaClientesResponse = null;
        listaClientes.forEach(p->{
            listaClientesResponse.add(new ClienteResponse(p));
        });




        log.info("[finaliza] - ClienteInfraRepository - listaDeClientes");
        return listaClientesResponse;
    }
}
