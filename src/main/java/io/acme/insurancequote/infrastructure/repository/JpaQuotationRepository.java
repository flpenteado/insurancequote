package io.acme.insurancequote.infrastructure.repository;


import io.acme.insurancequote.application.repository.QuotationRepository;
import io.acme.insurancequote.domain.models.Quotation;
import io.acme.insurancequote.infrastructure.repository.mapper.QuotationMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class JpaQuotationRepository implements QuotationRepository {

    private final EntityManager entityManager;

    public JpaQuotationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Quotation save(Quotation quotation) {

        var entity = QuotationMapper.toEntity(quotation);

        if (quotation.getId() == 0) {
            entityManager.persist(entity);

        } else {
            entityManager.merge(entity);
        }

        return QuotationMapper.toDomain(entity);
    }

    @Override
    public Optional<Quotation> findById(UUID id) {
        return Optional.empty();
    }
}
