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
    ResponseEntity<PedidoResponse> buscaPedidoPorId(@PathVariable @Valid UUID idPedido);
    @DeleteMapping("/deleta-pedido-por-id/{idPedido}")
    ResponseEntity deletaPedidoPorId(@PathVariable @Valid UUID idPedido);

    @PatchMapping("/atualiza-pedido/{idPedido}")
    ResponseEntity<PedidoResponse> atualizaPedido(@PathVariable @Valid UUID idPedido, PedidoRequest pedidoRequest);

    @DeleteMapping("/deleta-todos-pedidos")
    ResponseEntity deletaTodosPedidos();

}
