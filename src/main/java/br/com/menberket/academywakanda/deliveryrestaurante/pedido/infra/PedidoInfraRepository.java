package br.com.menberket.academywakanda.deliveryrestaurante.pedido.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.service.ClienteService;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.repository.PedidoRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
        UUID idCliente = clienteService.buscaClientePorId(pedidoRequest.getIdCliente()).getIdCliente();
        var pedido = new Pedido(pedidoRequest);
        pedidoSpringMongoDBRepository.save(pedido);
        log.info("[finaliza] - PedidoInfraRepository - PedidoRepository ");

    }

    @Override
    public List<PedidoResponse> buscaTodosPedidos() {
        log.info("[inicia] - PedidoInfraRepository - buscaTodosPedidos ");

        List<Pedido> pedido = pedidoSpringMongoDBRepository.findAll();
        List<PedidoResponse> pedidoResponse = convertListEmResponse(pedido);
        log.info("[finaliza] - PedidoInfraRepository - buscaTodosPedidos ");
        return pedidoResponse;
    }

    @Override
    public void deletaTodosPedidos() {
        log.info("[inicia] - PedidoInfraRepository - deletaTodosPedidos ");
        pedidoSpringMongoDBRepository.deleteAll();
        log.info("[finaliza] - PedidoInfraRepository - deletaTodosPedidos ");
    }

    @Override
    public void deletaPedidoPorId(UUID idPedido) {
        log.info("[inicia] - PedidoInfraRepository - deletaPedidoPorId ");
        pedidoSpringMongoDBRepository.deleteById(idPedido);
        log.info("[finaliza] - PedidoInfraRepository - deletaPedidoPorId ");
    }

    @Override
    public PedidoResponse buscaPedidoPorId(UUID idPedido) {
        log.info("[inicia] - PedidoInfraRepository - buscaPedidoPorId ");
        Pedido pedido = pedidoSpringMongoDBRepository.findById(idPedido).get();

        PedidoResponse pedidoResponse = new PedidoResponse(pedido);
        log.info("[finaliza] - PedidoInfraRepository - buscaPedidoPorId ");
        return pedidoResponse;
    }

    public List<PedidoResponse> convertListEmResponse(List<Pedido> pedido) {
        log.info("[inicio] - ClienteInfraRepository - convertListEmResponse");
        List<PedidoResponse> pedidoResponse = new ArrayList<PedidoResponse>();
        pedido.forEach(p -> {
            pedidoResponse.add(new PedidoResponse(p));
        });
        log.info("[finaliza] - ClienteInfraRepository - convertListEmResponse");
        return pedidoResponse;
    }
}
