package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.service;

import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;

import java.util.List;
import java.util.UUID;

public interface PedidoService {
    void salva( PedidoRequest pedidoRequest);

    List<PedidoResponse> buscaTodosPedidos();

    PedidoResponse buscaPedidoPorId(UUID idPedido);

    void deletaPedidoPorId(UUID idPedido);

    void deletaTodosPedidos();

    void atualizaPedido(UUID idPedido, PedidoRequest pedidoRequest);
    List<Pedido> buscaPedidoPorCliente(UUID idCliente);
}
