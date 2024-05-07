package br.com.menberket.academywakanda.deliveryrestaurante.entrega.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain.Entrega;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface EntregaSpringMongoDBRepository extends MongoRepository<Entrega, UUID> {
}
