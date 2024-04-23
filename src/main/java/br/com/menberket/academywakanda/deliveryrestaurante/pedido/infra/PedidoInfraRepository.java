package br.com.menberket.academywakanda.deliveryrestaurante.pedido.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service.ClienteService;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.repository.PedidoRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PedidoInfraRepository implements PedidoRepository {
    private final ClienteService clienteService;
    private final PedidoSpringMongoDBRepository pedidoSpringMongoDBRepository;

    @Override
    public void salva(PedidoRequest pedidoRequest) {
        log.info("[inicia] - PedidoInfraRepository - PedidoRepository ");
       UUID idCliente  = clienteService.buscaClientePorId(pedidoRequest.getIdCliente()).getIdCliente();
        var pedido = new Pedido(pedidoRequest);
        pedidoSpringMongoDBRepository.save(pedido);
        log.info("[finaliza] - PedidoInfraRepository - PedidoRepository ");

    }
}
