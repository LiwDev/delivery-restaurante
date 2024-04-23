package br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.service;

import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.api.PedidoRequest;
import br.com.menberket.academywakanda.deliveryrestaurante.pedido.application.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@Log4j2
@RequiredArgsConstructor
public class PedidoApplicationService implements  PedidoService{
    private final PedidoRepository pedidoRepository;

    @Override
    public void salva(PedidoRequest pedidoRequest) {
        log.info("[inicia] - PedidoApplicationService - salva ");
        pedidoRepository.salva(pedidoRequest);
        log.info("[inicia] - PedidoApplicationService - salva ");

    }
}
