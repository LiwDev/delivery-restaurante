package br.com.menberket.academywakanda.deliveryrestaurante.pedido.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PedidoSpringMongoDBRepository extends MongoRepository<Pedido, UUID> {
}
