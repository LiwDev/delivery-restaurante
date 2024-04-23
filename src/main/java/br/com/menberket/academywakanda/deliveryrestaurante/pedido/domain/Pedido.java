package br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain;

import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;
import io.swagger.v3.oas.models.media.DateTimeSchema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.format.datetime.standard.DateTimeFormatterFactoryBean;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.UUID;
@Data
public class Pedido {
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
    private LocalDateTime dataHoraPedido = LocalDateTime.now();

    public Pedido(PedidoRequest pedidoRequest) {
        setIdPedido(UUID.randomUUID());
        setIdCliente(pedidoRequest.getIdCliente());
        setNomeDoPrato(pedidoRequest.getNomeDoPrato());
        setGuarnicao(pedidoRequest.getGuarnicao());
        setCarne(pedidoRequest.getCarne());
        setBebida(pedidoRequest.getBebida());
        setArroz(pedidoRequest.getArroz());
        setFeijao(pedidoRequest.getFeijao());
        setQuantidade(pedidoRequest.getQuantidade());
        setPreco(pedidoRequest.getPreco());
        setDataHoraPedido(LocalDateTime.now());
    }
}
