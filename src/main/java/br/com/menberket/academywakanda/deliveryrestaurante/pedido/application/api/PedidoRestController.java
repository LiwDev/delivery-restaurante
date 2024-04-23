package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.repository.ClienteRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
@RequiredArgsConstructor
@Log4j2
public class PedidoRestController implements PedidoApi{


    private final PedidoService pedidoService;

    @Override
    public ResponseEntity criaNovoPedido(PedidoRequest pedidoRequest) {
        log.info("[inicia] - PedidoRestController - criaNovoPedido]");
        pedidoService.salva(pedidoRequest);
        log.info("[finaliza] - PedidoRestController - criaNovoPedido]");


        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
