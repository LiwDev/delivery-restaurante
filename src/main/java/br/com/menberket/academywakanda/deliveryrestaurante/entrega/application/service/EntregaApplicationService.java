package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.service;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.repository.ClienteRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api.EntregaResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.repository.EntregaRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain.Entrega;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.repository.PedidoRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.service.PedidoService;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class EntregaApplicationService implements EntregaService {
    private final EntregaRepository entregaRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoService pedidoService;


    @Override
    public ResponseEntity criaNovaEntrega(UUID idCliente) {
        log.info("[inicia] - EntregaApplicationService - criaNovaEntrega");
        Cliente cliente = new Cliente(clienteRepository.buscaClientePorId(idCliente));
        Entrega entrega = entregaRepository.criarNovaEntrega(new Entrega(cliente, pedidoService.buscaPedidoPorCliente(idCliente)));
        log.info("[finaliza] - EntregaApplicationService - criaNovaEntrega");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(entrega);
    }

    @Override
    public ResponseEntity<List<EntregaResponse>> buscaTodasEntregas() {
        log.info("[inicia] - EntregaApplicationService - buscaTodasEntregas");
        List<EntregaResponse> entregaResponse = entregaRepository.buscaTodasEntregas();
        log.info("[finaliza] - EntregaApplicationService - buscaTodasEntregas");

        return ResponseEntity.ok().body(entregaResponse);
    }

    @Override
    public ResponseEntity<List<EntregaResponse>> BuscaEntregaCliente(UUID idCliente) {
        log.info("[inicia] - BuscaEntregaCliente - BuscaEntregaCliente ");
        List<EntregaResponse> entregaResponse = entregaRepository.BuscaEntregaCliente(idCliente);
        ;
        log.info("[finaliza] - BuscaEntregaCliente - BuscaEntregaCliente");
        return ResponseEntity.ok().body(entregaResponse);
    }


}
