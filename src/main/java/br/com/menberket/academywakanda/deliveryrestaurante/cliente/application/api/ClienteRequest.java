package br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;
@Value
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

}
