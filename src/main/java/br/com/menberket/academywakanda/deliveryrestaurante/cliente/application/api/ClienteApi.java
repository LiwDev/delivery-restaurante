package br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("v1/cliente")
public interface ClienteApi {
    @PostMapping("/novo-cliente")
    ResponseEntity criaNovoCliente(@RequestBody @Valid ClienteRequest clienteRequest);

    @GetMapping("/busca-cliente-por-id/{idCliente}")
    ResponseEntity<ClienteResponse> buscaClientePorId(@PathVariable @Valid UUID idCliente);
    @GetMapping("/lista-Clientes")
    ResponseEntity<List<ClienteResponse>> listaDeClientes();

    @DeleteMapping("/deleta-cliente-por-id/{idCliente}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletaClientePorId(@Valid @PathVariable UUID idCliente);

}
