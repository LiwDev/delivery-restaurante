package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.service;

import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api.EntregaResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface EntregaService {
    ResponseEntity criaNovaEntrega(UUID idCliente);

    ResponseEntity<List<EntregaResponse>> buscaTodasEntregas();
}