package br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteRequest;


import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteResponse;
import lombok.*;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "cliente")
public class Cliente {
    @Id
    private UUID idCliente;
    @NotBlank(message = "Campo Obrigatorio")
    @Size(message = "Campo endereco do nomeCompleto não pode estar vazio", max = 255, min = 3)
    private String nomeCompleto;
    @NotBlank
    @Size(message = "Campo endereco do cpf não pode estar vazio", max = 255, min = 3)
    private String cpf;
    @NotBlank
    @Size(message = "Campo endereco do telefone não pode estar vazio", max = 255, min = 3)
    private String telefone;

    private String email;
    @NotBlank
    @Size(message = "Campo endereco do funcionario não pode estar vazio", max = 255, min = 3)
    private Endereco endereco;

    public Cliente(ClienteRequest clienteRequest) {
        setIdCliente(UUID.randomUUID());
        setNomeCompleto(clienteRequest.getNomeCompleto());
        setCpf(clienteRequest.getCpf());
        setTelefone(clienteRequest.getTelefone());
        setEmail(clienteRequest.getEmail());
        setEndereco(clienteRequest.getEndereco());
    }

    public Cliente(ClienteResponse clienteResponse) {
        setIdCliente(clienteResponse.getIdCliente());
        setNomeCompleto(clienteResponse.getNomeCompleto());
        setCpf(clienteResponse.getCpf());
        setTelefone(clienteResponse.getTelefone());
        setEmail(clienteResponse.getEmail());
        setEndereco(clienteResponse.getEndereco());
    }
}
