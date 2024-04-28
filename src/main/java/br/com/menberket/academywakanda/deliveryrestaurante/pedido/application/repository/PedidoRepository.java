package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.repository;

import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoResponse;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository {
    void salva(PedidoRequest pedidoRequest);

    List<PedidoResponse> buscaTodosPedidos();

    void deletaTodosPedidos();

    void deletaPedidoPorId(UUID idPedido);

    PedidoResponse buscaPedidoPorId(UUID idPedido);

    void atualizaPedido(UUID idPedido, PedidoRequest pedidoRequest);
}
