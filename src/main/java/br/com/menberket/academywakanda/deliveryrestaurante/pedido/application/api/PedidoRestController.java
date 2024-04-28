package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.repository.ClienteRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class PedidoRestController implements PedidoApi {


    private final PedidoService pedidoService;

    @Override
    public ResponseEntity criaNovoPedido(PedidoRequest pedidoRequest) {
        log.info("[inicia] - PedidoRestController - criaNovoPedido]");
        pedidoService.salva(pedidoRequest);
        log.info("[finaliza] - PedidoRestController - criaNovoPedido]");


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<PedidoResponse>> buscaTodosPedidos() {
        log.info("[inicio] - PedidoRestController - buscaTodosPedidos");
        List<PedidoResponse> pedido = pedidoService.buscaTodosPedidos();
        log.info("[inicio] - PedidoRestController - buscaTodosPedidos");
        return ResponseEntity.ok(pedido);
    }

    @Override
    public ResponseEntity<PedidoResponse> buscaPedidoPorId(UUID idPedido) {
        log.info("[inicio] - PedidoRestController - buscaPedidoPorId");
        PedidoResponse pedidoResponse = pedidoService.buscaPedidoPorId(idPedido);
        log.info("[finaliza] - PedidoRestController - buscaPedidoPorId");
        return ResponseEntity.ok(pedidoResponse);
    }

    @Override
    public ResponseEntity deletaPedidoPorId(UUID idPedido) {
        log.info("[inicio] - PedidoRestController - deletaPedidoPorId");
        pedidoService.deletaPedidoPorId(idPedido);
        log.info("[finaliza] - PedidoRestController - deletaPedidoPorId");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PedidoResponse> atualizaPedido(UUID idPedido, PedidoRequest pedidoRequest) {
        log.info("[inicio] - PedidoRestController - atualizaPedido");
            pedidoService.atualizaPedido(idPedido,pedidoRequest);
        log.info("[finaliza] - PedidoRestController - atualizaPedido");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @Override
    public ResponseEntity deletaTodosPedidos() {
        log.info("[inicio] - PedidoRestController - deletaTodosPedidos");
        pedidoService.deletaTodosPedidos();
        log.info("[finaliza] - PedidoRestController - deletaTodosPedidos");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
