package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/pedido")
public interface PedidoApi {

    @PostMapping("/cria-novo-pedido")
    ResponseEntity criaNovoPedido(@RequestBody PedidoRequest pedidoRequest);
    @GetMapping("/busca-todos-pedidos")
    ResponseEntity<List<PedidoResponse>> buscaTodosPedidos();
    @GetMapping("/busca-pedido-por-id/{idPedido}")
    ResponseEntity<PedidoResponse> buscaPedidoPorId(@Valid @PathVariable  UUID idPedido);
    @DeleteMapping("/deleta-pedido-por-id/{idPedido}")
    ResponseEntity deletaPedidoPorId(@Valid  @PathVariable UUID idPedido);

    @DeleteMapping("/deleta-todos-pedidos")
    ResponseEntity deletaTodosPedidos();

}
