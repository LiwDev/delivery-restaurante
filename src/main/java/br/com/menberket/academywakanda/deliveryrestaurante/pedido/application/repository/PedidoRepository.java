package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.repository;

import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;

import java.util.UUID;

public interface PedidoRepository {
    void salva(PedidoRequest pedidoRequest);
}
