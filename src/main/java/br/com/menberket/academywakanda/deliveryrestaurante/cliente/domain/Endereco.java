package br.com.menberket.academywakanda.deliveryrestaurante.cliente.domain;

import br.com.menberket.academywakanda.deliveryrestaurante.cliente.application.api.ClienteRequest;
import lombok.*;




@Data
public class Endereco {

    private String nomeDaRua;

    private String nomeBairro;

    private String complemento;

    private int numero;

    private String cep;

}
