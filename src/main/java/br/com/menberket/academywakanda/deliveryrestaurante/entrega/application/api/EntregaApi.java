package br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("v1/entrega")
public interface EntregaApi {
    @PostMapping("/cria-nova-entrega/{idCliente}")
ResponseEntity criaNovaEntrega(@PathVariable @Valid  UUID idCliente);
}
