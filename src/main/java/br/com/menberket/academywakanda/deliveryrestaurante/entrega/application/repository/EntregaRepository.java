package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.repository;

import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api.EntregaResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain.Entrega;

import java.util.List;
import java.util.UUID;

public interface EntregaRepository {
    Entrega criarNovaEntrega(Entrega entrega);

    List<EntregaResponse> buscaTodasEntregas();

    List<EntregaResponse> BuscaEntregaCliente(UUID idCliente);
}
