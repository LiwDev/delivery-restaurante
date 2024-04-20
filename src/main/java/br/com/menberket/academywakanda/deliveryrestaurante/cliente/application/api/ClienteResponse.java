package br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Cliente;
import br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain.Endereco;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
public class ClienteResponse {
    private UUID idCliente;
    private String nomeCompleto;
    private String cpf;
    private String telefone;
    private String email;
    private Endereco endereco;

    public ClienteResponse(Cliente cliente) {
        setIdCliente(cliente.getIdCliente());
        setNomeCompleto(cliente.getNomeCompleto());
        setCpf(cliente.getCpf());
        setTelefone(cliente.getTelefone());
        setEmail(cliente.getEmail());
        setEndereco(cliente.getEndereco());
    }
}
