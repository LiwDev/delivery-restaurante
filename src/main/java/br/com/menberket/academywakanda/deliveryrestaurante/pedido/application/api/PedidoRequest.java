package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.*;
import lombok.Getter;

import java.util.UUID;
@Getter
public class PedidoRequest {

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
}
