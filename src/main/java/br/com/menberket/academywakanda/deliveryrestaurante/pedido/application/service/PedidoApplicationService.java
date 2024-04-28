package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.service;

import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@Log4j2
@RequiredArgsConstructor
public class PedidoApplicationService implements  PedidoService{
    private final PedidoRepository pedidoRepository;

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
}
