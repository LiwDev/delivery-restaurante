package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.repository;

import br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain.Entrega;

public interface EntregaRepository {
    Entrega criarNovaEntrega(Entrega entrega);
}
