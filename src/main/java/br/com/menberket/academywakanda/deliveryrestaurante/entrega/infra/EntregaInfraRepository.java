package br.com.menberket.academywakanda.deliveryrestaurante.entrega.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.api.EntregaResponse;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.repository.EntregaRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain.Entrega;
import br.com.menberket.academywakanda.deliveryrestaurante.handler.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Log4j2
@RequiredArgsConstructor
public class EntregaInfraRepository implements EntregaRepository {
    private final EntregaSpringMongoDBRepository entregaSpringMongoDBRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public Entrega criarNovaEntrega(Entrega entrega) {
        log.info("[inicia] - EntregaInfraRepository - criarNovaEntrega");
        try {
            entregaSpringMongoDBRepository.save(entrega);
        } catch (DataIntegrityViolationException e) {
            throw ApiException.build(HttpStatus.BAD_REQUEST, "Entrega já cadastrada", e);
        }
        log.info("[finaliza] - EntregaInfraRepository - criarNovaEntrega");

        return entrega;
    }

    @Override
    public List<EntregaResponse> buscaTodasEntregas() {
        log.info("[inicia] - EntregaInfraRepository - buscaTodasEntregas");
        List<EntregaResponse> entrega;
        try {
            entrega = entregaSpringMongoDBRepository.findAll().stream().map(EntregaResponse::new).toList();
        } catch (NullPointerException e) {
            throw ApiException.build(HttpStatus.NOT_FOUND, "Não Ha Entregas Cadastradas", e);
        }
        log.info("[finaliza] - EntregaInfraRepository - buscaTodasEntregas");
        return entrega;
    }

    @Override
    public List<EntregaResponse> BuscaEntregaCliente(UUID idCliente) {
        log.info("[inicia] -EntregaInfraRepository - BuscaEntregaCliente  ");
        List<EntregaResponse> entregaResponses;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("cliente._id").is(idCliente));
            entregaResponses = mongoTemplate.find(query, Entrega.class).stream().map(EntregaResponse::new).toList();
        } catch (NullPointerException e) {
            throw ApiException.build(HttpStatus.NOT_FOUND, "Entrega não encontrada", e);
        }
        log.info("finaliza -EntregaInfraRepository - BuscaEntregaCliente ");
        return entregaResponses;
    }
}
