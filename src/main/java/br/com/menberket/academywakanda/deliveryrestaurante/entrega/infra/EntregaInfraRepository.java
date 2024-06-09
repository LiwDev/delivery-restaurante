package br.com.menberket.academywakanda.deliveryrestaurante.entrega.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.infra.ClienteInfraRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api.EntregaRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api.EntregaResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.repository.EntregaRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain.Entrega;
import br.com.menberket.academywakanda.deliveryrestaurante.handler.ApiException;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.infra.PedidoInfraRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
@Log4j2
@RequiredArgsConstructor
public class EntregaInfraRepository implements EntregaRepository {
    private final EntregaSpringMongoDBRepository entregaSpringMongoDBRepository;
    private final MongoTemplate mongoTemplate;
   private final PedidoInfraRepository pedidoInfraRepository;
   private final ClienteInfraRepository clienteInfraRepository;

    @Override
    public Entrega criarNovaEntrega(Entrega entrega) {
        log.info("[inicia] - EntregaInfraRepository - criarNovaEntrega");
        try {
            entregaSpringMongoDBRepository.save(entrega);
        } catch (DataIntegrityViolationException e) {
            throw ApiException.build(HttpStatus.BAD_REQUEST, "Entrega já cadastrada", e);
        }
        log.info("[finaliza] - EntregaInfraRepository - criarNovaEntrega");

        return entrega;
    }

    @Override
    public List<EntregaResponse> buscaTodasEntregas() {
        log.info("[inicia] - EntregaInfraRepository - buscaTodasEntregas");
        List<EntregaResponse> entrega;
        try {
            entrega = entregaSpringMongoDBRepository.findAll().stream().map(EntregaResponse::new).toList();
        } catch (NullPointerException e) {
            throw ApiException.build(HttpStatus.NOT_FOUND, "Não Ha Entregas Cadastradas", e);
        }
        log.info("[finaliza] - EntregaInfraRepository - buscaTodasEntregas");
        return entrega;
    }

    @Override
    public List<EntregaResponse> BuscaEntregaCliente(UUID idCliente) {
        log.info("[inicia] -EntregaInfraRepository - BuscaEntregaCliente  ");
        List<EntregaResponse> entregaResponses;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("cliente._id").is(idCliente));
            entregaResponses = mongoTemplate.find(query, Entrega.class).stream().map(EntregaResponse::new).toList();
        } catch (NullPointerException e) {
            throw ApiException.build(HttpStatus.NOT_FOUND, "Entrega não encontrada", e);
        }
        log.info("finaliza -EntregaInfraRepository - BuscaEntregaCliente ");
        return entregaResponses;
    }


    @Override
    public ResponseEntity atualizaEntrega(UUID idCliente, EntregaRequest entregaRequest) {
        UpdateResult result = null;
        List<Entrega> entregaList = BuscaEntregaCliente(idCliente).stream().map(Entrega::new).toList();
        UUID idEntrega = entregaList.get(0).getIdEntrega();
        UUID idPedido = pedidoInfraRepository.buscaPedidoPorIdCliente(idCliente).getIdPedido();

        if (idEntrega != null) {
            Query query = new Query();
            query.addCriteria(Criteria.where("idEntrega").is(idEntrega));
            Update update = new Update()
                    .set("cliente", entregaRequest.getCliente())
                    .set("pedidos", entregaRequest.getPedidos());
            result = mongoTemplate.updateFirst(query, update, Entrega.class);
            clienteInfraRepository.atualizaCliente(idCliente, new ClienteRequest(entregaRequest.getCliente()));
            pedidoInfraRepository.atualizaPedido(idPedido, new PedidoRequest(entregaRequest.getPedidos().get(0)));
        }
        log.info("[inicia] -EntregaInfraRepository - atualizaEntrega");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
    }

    public  void atualizaPedidoDaEntrega(UUID idPedido ,Update update){
        Query query = new Query();
        query.addCriteria(Criteria.where("pedidos._idPedido").is(idPedido));
         mongoTemplate.updateFirst(query, update, Entrega.class);
    }


}
