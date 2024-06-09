package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@RequiredArgsConstructor
public class PedidoRequest {
    private UUID idCliente;
    private String nomeDoPrato;
    private Guarnicao guarnicao;
    private Carne carne;
    private Bebida bebida;
    private Arroz arroz;
    private Feijao feijao;
    private int quantidade;
    private double preco;



    public PedidoRequest(Pedido pedido) {
        this.idCliente = pedido.getIdCliente();
        this.nomeDoPrato = pedido.getNomeDoPrato();;
        this.guarnicao = pedido.getGuarnicao();
        this.carne = pedido.getCarne();
        this.bebida = pedido.getBebida();
        this.arroz = pedido.getArroz();
        this.feijao = pedido.getFeijao();
        this.quantidade = pedido.getQuantidade();
        this.preco = pedido.getPreco();
    }


}
