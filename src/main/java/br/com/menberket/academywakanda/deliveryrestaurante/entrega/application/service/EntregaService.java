package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.service;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface EntregaService {
    ResponseEntity criaNovaEntrega(UUID idCliente);
}
