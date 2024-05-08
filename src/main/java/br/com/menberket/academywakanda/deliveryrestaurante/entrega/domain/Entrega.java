package br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Entrega {
    @Id
private UUID idEntrega;
private Cliente cliente;
private List<Pedido> pedidos;

    public Entrega(Cliente cliente,List<Pedido> pedido) {
        this.idEntrega = UUID.randomUUID();
        this.cliente = cliente;
        this.pedidos = pedido;
    }
}
