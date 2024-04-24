package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.*;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PedidoResponse {
    private UUID idPedido;
    private UUID idCliente;
    private String nomeDoPrato;
    private Guarnicao guarnicao;
    private Carne carne;
    private Bebida bebida;
    private Arroz arroz;
    private Feijao feijao;
    private int quantidade;
    private double preco;

    public PedidoResponse(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.idCliente = pedido.getIdCliente();
        this.nomeDoPrato = pedido.getNomeDoPrato();
        this.guarnicao = pedido.getGuarnicao();
        this.carne = pedido.getCarne();
        this.bebida = pedido.getBebida();
        this.arroz = pedido.getArroz();
        this.feijao = pedido.getFeijao();
        this.quantidade = pedido.getQuantidade();
        this.preco = pedido.getPreco();
    }
}
