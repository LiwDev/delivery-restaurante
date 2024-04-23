package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("v1/pedido")
public interface PedidoApi {

    @PostMapping("/cria-novo-pedido")
    ResponseEntity criaNovoPedido(@RequestBody PedidoRequest pedidoRequest);
}
