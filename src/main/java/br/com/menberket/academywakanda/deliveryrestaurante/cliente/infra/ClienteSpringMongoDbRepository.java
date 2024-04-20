package br.com.menberket.academywakanda.deliveryrestaurante.cliente.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ClienteSpringMongoDbRepository extends MongoRepository<Cliente, UUID> {
}
