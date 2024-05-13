package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.service;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.repository.ClienteRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain.Entrega;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.repository.PedidoRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@Log4j2
@RequiredArgsConstructor
public class PedidoApplicationService implements  PedidoService{
    private final PedidoRepository pedidoRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void salva(PedidoRequest pedidoRequest) {
        log.info("[inicia] - PedidoApplicationService - salva ");
        pedidoRepository.salva(pedidoRequest);
        log.info("[inicia] - PedidoApplicationService - salva ");

    }

    @Override
    public List<PedidoResponse> buscaTodosPedidos() {
        log.info("[inicia] - PedidoApplicationService - buscaTodosPedidos ");
        List<PedidoResponse> pedidoResponse = pedidoRepository.buscaTodosPedidos();
        log.info("[finaliza] - PedidoApplicationService - buscaTodosPedidos ");
        return pedidoResponse;
    }

    @Override
    public PedidoResponse buscaPedidoPorId(UUID idPedido) {
        log.info("[inicia] - PedidoApplicationService - buscaPedidoPorId ");
        PedidoResponse pedidoResponse = pedidoRepository.buscaPedidoPorId(idPedido);
        log.info("[finaliza] - PedidoApplicationService - buscaPedidoPorId ");
        return pedidoResponse;
    }

    @Override
    public void deletaPedidoPorId(UUID idPedido) {
        log.info("[inicia] - PedidoApplicationService - buscaPedidoPorId ");
pedidoRepository.deletaPedidoPorId(idPedido);
        log.info("[finaliza] - PedidoApplicationService - buscaPedidoPorId ");
    }

    @Override
    public void deletaTodosPedidos() {
        log.info("[inicia] - PedidoApplicationService - buscaPedidoPorId ");
pedidoRepository.deletaTodosPedidos();
        log.info("[finaliza] - PedidoApplicationService - buscaPedidoPorId ");
    }

    @Override
    public void atualizaPedido(UUID idPedido, PedidoRequest pedidoRequest) {
        log.info("[inicia] - PedidoApplicationService - buscaPedidoPorId ");
        pedidoRepository.atualizaPedido(idPedido,pedidoRequest);
        log.info("[inicia] - PedidoApplicationService - buscaPedidoPorId ");
    }
    public List<Pedido> buscaPedidoPorCliente(UUID idCliente){
        log.info("[inicia] - PedidoApplicationService - buscaPedidoPorCliente ");
        Query query = new Query();
        query.addCriteria(Criteria.where("idCliente").is(idCliente));
        List<Pedido> pedidos =  mongoTemplate.find(query,Pedido.class);
        log.info("[inicia] - PedidoApplicationService - buscaPedidoPorCliente");
        return pedidos;
    }
}
