package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.service;

import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;

import java.util.UUID;

public interface PedidoService {
    void salva( PedidoRequest pedidoRequest);
}
