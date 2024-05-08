package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.repository;

import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api.EntregaResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain.Entrega;

import java.util.List;

public interface EntregaRepository {
    Entrega criarNovaEntrega(Entrega entrega);

    List<EntregaResponse> buscaTodasEntregas();
}
