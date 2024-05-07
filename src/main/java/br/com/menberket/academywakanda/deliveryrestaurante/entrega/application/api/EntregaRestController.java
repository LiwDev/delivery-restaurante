package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.service.EntregaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
@Log4j2
@RequiredArgsConstructor
public class EntregaRestController implements EntregaApi{
    private final EntregaService entregaService;
    @Override
    public ResponseEntity criaNovaEntrega(UUID idCliente) {
        log.info("[inicia] - EntregaRestController - criaNovaEntrega ");
        ResponseEntity response = entregaService.criaNovaEntrega(idCliente);
        log.info("[finaliza] - EntregaRestController - criaNovaEntrega ");
        return response;
    }
}
