package br.com.menberket.academywakanda.deliveryrestaurante.pedido.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service.ClienteService;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import br.com.menberket.academywakanda.deliveryrestaurante.handler.ApiException;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.repository.PedidoRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PedidoInfraRepository implements PedidoRepository {
    private final ClienteService clienteService;
    private final PedidoSpringMongoDBRepository pedidoSpringMongoDBRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void salva(PedidoRequest pedidoRequest) {
        log.info("[inicia] - PedidoInfraRepository - PedidoRepository ");
        UUID idCliente = clienteService.buscaClientePorId(pedidoRequest.getIdCliente()).getIdCliente();
        var pedido = new Pedido(pedidoRequest);
        pedidoSpringMongoDBRepository.save(pedido);
        log.info("[finaliza] - PedidoInfraRepository - PedidoRepository ");

    }

    @Override
    public List<PedidoResponse> buscaTodosPedidos() {
        log.info("[inicia] - PedidoInfraRepository - buscaTodosPedidos ");

        List<Pedido> pedido = pedidoSpringMongoDBRepository.findAll();
        List<PedidoResponse> pedidoResponse = convertListEmResponse(pedido);
        log.info("[finaliza] - PedidoInfraRepository - buscaTodosPedidos ");
        return pedidoResponse;
    }

    @Override
    public void deletaTodosPedidos() {
        log.info("[inicia] - PedidoInfraRepository - deletaTodosPedidos ");
        pedidoSpringMongoDBRepository.deleteAll();
        log.info("[finaliza] - PedidoInfraRepository - deletaTodosPedidos ");
    }

    @Override
    public void deletaPedidoPorId(UUID idPedido) {
        log.info("[inicia] - PedidoInfraRepository - deletaPedidoPorId ");
        pedidoSpringMongoDBRepository.deleteById(idPedido);
        log.info("[finaliza] - PedidoInfraRepository - deletaPedidoPorId ");
    }

    @Override
    public PedidoResponse buscaPedidoPorId(UUID idPedido) {
        log.info("[inicia] - PedidoInfraRepository - buscaPedidoPorId ");
        Pedido pedido = pedidoSpringMongoDBRepository.findById(idPedido).get();
        PedidoResponse pedidoResponse = new PedidoResponse(pedido);
        log.info("[finaliza] - PedidoInfraRepository - buscaPedidoPorId ");
        return pedidoResponse;
    }

    @Override
    public void atualizaPedido(UUID idPedido, PedidoRequest pedidoRequest) {
        log.info("[inicia] - PedidoInfraRepository - atualizaPedido ");
        try {
            Query query = new Query();

            query.addCriteria(Criteria.where("idPedido").is(idPedido));
            Update update = new Update()
                    .set("arroz", pedidoRequest.getArroz())
                    .set("carne", pedidoRequest.getCarne())
                    .set("bebida", pedidoRequest.getBebida())
                    .set("feijao", pedidoRequest.getFeijao())
                    .set("nomeDoPrato", pedidoRequest.getNomeDoPrato())
                    .set("guarnicao", pedidoRequest.getGuarnicao())
                    .set("preco", pedidoRequest.getPreco())
                    .set("quantidade", pedidoRequest.getQuantidade());

            mongoTemplate.updateFirst(query, update, Pedido.class);
        } catch (NullPointerException nullPointerException) {
            throw ApiException.build(HttpStatus.BAD_REQUEST, "ID do Pedido invalido", nullPointerException);
        }
        log.info("[finaliza] - PedidoInfraRepository - atualizaPedido ");
    }

    public List<PedidoResponse> convertListEmResponse(List<Pedido> pedido) {
        log.info("[inicio] - ClienteInfraRepository - convertListEmResponse");
        List<PedidoResponse> pedidoResponse = new ArrayList<PedidoResponse>();
        pedido.forEach(p -> {
            pedidoResponse.add(new PedidoResponse(p));
        });
        log.info("[finaliza] - ClienteInfraRepository - convertListEmResponse");
        return pedidoResponse;
    }
}
