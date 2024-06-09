package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/entrega")
public interface EntregaApi {
    @PostMapping("/cria-nova-entrega/{idCliente}")
ResponseEntity criaNovaEntrega(@PathVariable @Valid  UUID idCliente);

    @GetMapping("/busca-todas-entregas")
    ResponseEntity<List<EntregaResponse>> buscaTodasEntregas();
    @GetMapping("/busca-entrega-por-cliente/{idCliente}")
    ResponseEntity<List<EntregaResponse>> BuscaEntregaCliente(@PathVariable @Valid UUID idCliente);

    @PatchMapping("/atualiza-entrega/{idCliente}")
    ResponseEntity  atualizaEntrega(@PathVariable @Valid UUID idCliente,@RequestBody EntregaRequest entregaRequest);
}
