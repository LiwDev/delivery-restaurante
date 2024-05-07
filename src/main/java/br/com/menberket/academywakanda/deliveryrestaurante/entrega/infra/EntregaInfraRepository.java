package br.com.menberket.academywakanda.deliveryrestaurante.entrega.infra;

import br.com.menberket.academywakanda.deliveryrestaurante.entrega.application.repository.EntregaRepository;
import br.com.menberket.academywakanda.deliveryrestaurante.entrega.domain.Entrega;
import br.com.menberket.academywakanda.deliveryrestaurante.handler.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
@RequiredArgsConstructor
public class EntregaInfraRepository implements EntregaRepository {
    private final EntregaSpringMongoDBRepository entregaSpringMongoDBRepository;

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
}
