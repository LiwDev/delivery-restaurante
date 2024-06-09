package br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.UUID;
@Value
@RequiredArgsConstructor
public class ClienteRequest {
    @NotBlank
    private String nomeCompleto;
    @NotBlank
    private String cpf;
    @NotBlank
    private String telefone;
    @NotBlank
    private String email;
    @NotBlank
    private Endereco endereco;

    public ClienteRequest(Cliente cliente) {
        this.nomeCompleto = cliente.getNomeCompleto();
        this.cpf =cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
        this.endereco = cliente.getEndereco();
    }
}
