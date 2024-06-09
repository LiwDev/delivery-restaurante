package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
@Getter
public class EntregaRequest {
    private Cliente cliente;
    private List<Pedido> pedidos;
}
