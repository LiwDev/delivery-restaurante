package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain.Entrega;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
@Getter
public class EntregaResponse {
    private UUID idEntrega;
    private Cliente cliente;
    private List<Pedido> pedidos;

    public EntregaResponse(Entrega entrega) {
        this.idEntrega = entrega.getIdEntrega();
        this.cliente = entrega.getCliente();
        this.pedidos = entrega.getPedidos();
    }

    public EntregaResponse(List<Entrega> entregas) {
        entregas.forEach(EntregaResponse::new);
    }
}
