package br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Document(collection = "entrega")
public class Entrega {
    @Id
    private UUID idEntrega;
    private Cliente cliente;
    private List<Pedido> pedidos;

    public Entrega(Cliente cliente, List<Pedido> pedido) {
        setIdEntrega(UUID.randomUUID());
        setCliente(cliente);
        setPedidos(pedido);
    }

}
