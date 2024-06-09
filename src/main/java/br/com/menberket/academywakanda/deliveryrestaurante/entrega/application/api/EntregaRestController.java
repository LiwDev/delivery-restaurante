package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.service.EntregaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @Override
    public ResponseEntity<List<EntregaResponse>> buscaTodasEntregas() {
        log.info("[inicia] - EntregaRestController - buscaTodasEntregas");
        ResponseEntity<List<EntregaResponse>> response = entregaService.buscaTodasEntregas();
        log.info("[finaliza] - EntregaRestController - buscaTodasEntregas ");
        return response;
    }

    @Override
    public ResponseEntity<List<EntregaResponse>> BuscaEntregaCliente(UUID idCliente) {
        log.info("[inicia]- EntregaRestController - BuscaEntregaCliente ");
        ResponseEntity<List<EntregaResponse>> response = entregaService.BuscaEntregaCliente(idCliente);
        log.info("[finaliza]- EntregaRestController - BuscaEntregaCliente ");
        return response;
    }

    @Override
    public ResponseEntity atualizaEntrega(UUID idCliente, EntregaRequest entregaRequest) {
        log.info("[inicia]- EntregaRestController - atualizaEntrega ");
        ResponseEntity response = entregaService.atualizaEntrega(idCliente,entregaRequest);
        log.info("[inicia]- EntregaRestController - atualizaEntrega ");
        return null;
    }
}
